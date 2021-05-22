package ca.nait.dmit.listviewdemo_countrycapital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    val countryEditText: EditText by lazy { findViewById(R.id.activity_main_countryEditText) }
    val capitalEditText: EditText by lazy { findViewById(R.id.activity_main_capitalEditText) }
    val countryListView: ListView by lazy { findViewById(R.id.activity_main_countryListView)}
    val countryDataAdapter = CountryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countryListView.adapter = countryDataAdapter
    }

    fun onSubmitClick(view: View) {
        val countryName = countryEditText.text.toString()
        val capitalCityName = capitalEditText.text.toString()

        val newCountry = Country(countryName, capitalCityName)
        countryDataAdapter.addItem(newCountry)

        // Clear the edit text fields to allow entry of another country
        countryEditText.setText("")
        capitalEditText.setText("")

    }
}