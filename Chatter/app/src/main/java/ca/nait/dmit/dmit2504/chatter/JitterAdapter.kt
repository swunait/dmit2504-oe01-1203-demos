package ca.nait.dmit.dmit2504.chatter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class JitterAdapter : BaseAdapter(){
    private val jitterList = mutableListOf<Jitter>()

    override fun getCount(): Int {
        return jitterList.size
    }

    override fun getItem(position: Int): Any {
        return jitterList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflatedView = LayoutInflater.from(parent?.context).inflate(R.layout.custom_row, parent, false)

        // Find all the views in the list item
        val dateTextView: TextView = inflatedView.findViewById(R.id.custom_row_date)
        val dataTextView: TextView = inflatedView.findViewById(R.id.custom_row_text)
        val loginNameText: TextView = inflatedView.findViewById(R.id.custom_row_sender)

        // Set the text for each view in the list item
        dateTextView.text = jitterList[position].date
        dataTextView.text = jitterList[position].data
        loginNameText.text = jitterList[position].loginName

        return inflatedView
    }

    fun addItem(newJitter: Jitter) {
        jitterList.add(newJitter)
        notifyDataSetChanged()
    }
}