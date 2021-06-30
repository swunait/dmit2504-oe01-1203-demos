package ca.nait.dmit.backgroundprocessingdemo

import JitterDatabase
import NetworkAPI
import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.BufferedReader
import java.io.StringReader

const val WORKER_INPUT_DATA = "ca.nait.dmit.dmit2504.SYNC_URL"
const val WORKER_OUTPUT_DATA = "ca.nait.dmit.dmit2504_SYNC_COUNT"

class JitterDatabaseSyncWorker(context: Context, workParams: WorkerParameters)
    : Worker(context, workParams) {

    companion object {
        const val SYNC_URL = "https://capstone1.app.dmitcapstone.ca/api/jitters/Week05Servlet"
    }

    override fun doWork(): Result {
        val syncUrl = inputData.getString(WORKER_INPUT_DATA) ?: SYNC_URL

        var recordsImported = 0
        // Create an instance of the NetworkAPI
        val networkAPI = NetworkAPI()
        val responseData = networkAPI.fetchUrlResponseString(syncUrl)
        val reader = BufferedReader(StringReader(responseData))
        var currentLine: String?
        val database = JitterDatabase(applicationContext)
        while ( (reader.readLine().also{ currentLine = it }) != null) {
            val jitterId = currentLine!!.toInt()
            val loginName = reader.readLine()
            val message = reader.readLine()
            val date = reader.readLine()

            if (database.findById(jitterId).count != 1) {
                val rowID = database.insertRecord(jitterId, loginName, message, date)
                if (rowID != -1L) {
                    recordsImported++
                }
            }
        }

        val outputData = Data.Builder()
            .putInt(WORKER_OUTPUT_DATA, recordsImported)
            .build()

        return Result.success(outputData)
    }

}