package ca.nait.dmit.backgroundprocessingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.loader.app.LoaderManager
import androidx.work.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                    //LoaderManager.getInstance(this).restartLoader(1, null, this)
                }
            })
    }
}