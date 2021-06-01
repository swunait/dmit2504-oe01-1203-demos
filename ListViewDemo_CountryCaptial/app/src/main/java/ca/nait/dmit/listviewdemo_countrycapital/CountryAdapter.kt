package ca.nait.dmit.listviewdemo_countrycapital

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CountryAdapter : BaseAdapter() {

    private val countryList = mutableListOf<Country>()

    override fun getCount(): Int {
        return countryList.size
    }

    override fun getItem(position: Int): Any {
        //return countryList[position]
        return countryList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(parent?.context)
        val inflatedView = inflater.inflate(R.layout.list_item_country, parent, false)

        val countryTextView = inflatedView.findViewById<TextView>(R.id.list_item_countryTextView)
        val capitalTextView: TextView = inflatedView.findViewById(R.id.list_item_capitalTextView)

        //val currentCountry = countryList[position]
        val currentCountry = getItem(position) as Country
        countryTextView.text = currentCountry.name
        capitalTextView.text = currentCountry.capital

        return inflatedView
    }

    fun addItem(newCountry: Country) {
        countryList.add(newCountry)
        notifyDataSetChanged()
    }

}