package ca.nait.dmit.exercisetracklistviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class TrackAdapter(trackList : List<Track>) : BaseAdapter() {

    private val _trackList : List<Track>

    init {
        _trackList = trackList
    }

    override fun getCount(): Int {
       return _trackList.size
    }

    override fun getItem(position: Int): Track {
        return _trackList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Inflate the XML Layout list view
        val inflatedView = LayoutInflater
            .from(parent?.context)
            .inflate(R.layout.list_item_track, parent, false)

        // Find the views inside the list item
        val trackNameTextView: TextView = inflatedView.findViewById(R.id.list_item_trackname_textview)
        val trackPriceTextView : TextView = inflatedView.findViewById(R.id.list_item_trackprice_textview)
        val trackDateTextView : TextView = inflatedView.findViewById(R.id.list_item_trackdate_textview)

        // Get the current item to display
//        val currentTrack = _trackList[position]
        val currentTrack = getItem(position)

        // Change the text for each view in the list item
        trackNameTextView.setText(currentTrack.trackName)
        trackPriceTextView.setText(String.format("$%.2f",currentTrack.trackPrice))
        trackDateTextView.setText(currentTrack.trackDate.toString())

        // return the inflatedView
        return inflatedView
    }
}