package ca.nait.dmit.backgroundprocessingdemo

import JitterDatabase
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import androidx.work.*

const val INTENT_ACTION_DATA_CHANGED = "INTENT_ACTION_DATA_CHANGED"
const val EXTRA_EDIT_ID = "EXTRA_EDIT_ID"

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    private val jittersListView: ListView by lazy {findViewById(R.id.activity_main_jitters_listview)}
    private var jittersCursorAdapter: JitterCursorAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = JitterDatabase(this)
        LoaderManager.getInstance(this).initLoader(1, null, this)
        jittersCursorAdapter = JitterCursorAdapter(this, database.getAllRecords(), 0)
        jittersListView.adapter = jittersCursorAdapter
    }

    fun onSyncButtonClick(view: View) {

        // Create the input data
        val workerInputData = Data.Builder()
            .putString(WORKER_INPUT_DATA,"https://capstone1.app.dmitcapstone.ca/api/jitters/Week05Servlet")
            .build()

        // Create any constraints
        val networkConstraints = Constraints
            .Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // Create the WorkRequest
        val syncDatabaseRequest = OneTimeWorkRequest
            .Builder(JitterDatabaseSyncWorker::class.java)
            .setConstraints(networkConstraints)
            .setInputData(workerInputData)
            .build()

        // Create the WorkManager
        val workManager = WorkManager.getInstance(this)
        // Submit the WorkRequest to the system
        workManager
            .beginWith(syncDatabaseRequest)
            .enqueue()

        // Observe the results
        workManager.getWorkInfoByIdLiveData(syncDatabaseRequest.id)
            .observe(this, Observer { workInfo ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    val syncCount = workInfo.outputData.getInt(WORKER_OUTPUT_DATA, 0)
                    Toast.makeText(this, "Worked done syncing $syncCount records", Toast.LENGTH_LONG).show()
                    LoaderManager.getInstance(this).restartLoader(1, null, this)
                }
            })
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return JitterCursorLoader(this)
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        jittersCursorAdapter?.swapCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        // Set the old cursor to null to invalidate and we don't need a reference around
        jittersCursorAdapter?.swapCursor(null)
    }


    inner class CursorAdapterBroadcastReceiver: BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == INTENT_ACTION_DATA_CHANGED) {
                val editId = intent?.getIntExtra(EXTRA_EDIT_ID,0)
                Toast.makeText(this@MainActivity,"Dataset Changed with id $editId", Toast.LENGTH_LONG).show()
                LoaderManager.getInstance(this@MainActivity).restartLoader(editId, null, this@MainActivity)
            }
        }
    }

    val updateJitterDataBroadcastReceiver = CursorAdapterBroadcastReceiver()

    override fun onResume() {
        super.onResume()

        val dataChangedIntentFilter = IntentFilter().apply {
            addAction(INTENT_ACTION_DATA_CHANGED)
        }

        registerReceiver(updateJitterDataBroadcastReceiver, dataChangedIntentFilter)
    }

    override fun onPause() {
        super.onPause()

        unregisterReceiver(updateJitterDataBroadcastReceiver)
    }

}