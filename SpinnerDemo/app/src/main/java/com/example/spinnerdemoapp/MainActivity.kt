package com.example.spinnerdemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    val countrySpinner: Spinner by lazy { findViewById(R.id.countrySpinner) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countrySpinner.onItemSelectedListener = this
    }

    fun onSubmitButtonClick(view: View) {
        val selectedCountry : String = countrySpinner.selectedItem as String
        Toast.makeText(this,"You selected ${selectedCountry}", Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val countryNames = resources.getStringArray(R.array.country_names)
        val selectedCountryName = countryNames[position]
        Toast.makeText(this,"You selected ${selectedCountryName}", Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show()
    }

}