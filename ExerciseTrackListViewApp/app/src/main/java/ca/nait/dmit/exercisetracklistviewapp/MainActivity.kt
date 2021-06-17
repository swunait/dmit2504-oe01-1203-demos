package ca.nait.dmit.exercisetracklistviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import java.time.LocalDate
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val trackListView: ListView by lazy { findViewById(R.id.activity_main_track_listview) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Create a new list of Track
        var trackList = mutableListOf<Track>();
        // Random generate 50 Track objects and add it to the trackList
        var trackDate = LocalDate.now()
        for (trackNo in 1..50) {
            // Create new values for trackName, trackPrice, trackDate
            val trackName = "Track #$trackNo"
            // Generate random price between $0.00 to $10.00
            val trackPrice = Random.nextDouble(0.00, 10.00)
            trackDate = trackDate.minusDays(1)
            // Create a new Track instance
            val currentTrack = Track(trackName, trackPrice, trackDate)
            // Add the currentTrack to trackList
            trackList.add(currentTrack)
        }

        // Create and assign the adapter for the list view
        val trackDataAdapter = TrackAdapter(trackList)
        trackListView.adapter = trackDataAdapter

        // Set the ItemClick event for the list view
//        trackListView.setOnItemClickListener(object: AdapterView.OnItemClickListener {
//            override fun onItemClick(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                // Get the current item
//                val currentTrack = trackList[position]
//                val message = "You selected ${currentTrack.trackName}"
//                Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
//            }
//        })

        trackListView.setOnItemClickListener { parent, view, position, id ->
            // Get the current item
            val currentTrack = trackList[position]
            val message = "You selected ${currentTrack.trackName}"
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
        }


    }
}