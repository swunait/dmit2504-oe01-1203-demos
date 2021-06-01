package com.example.spinnerdemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    val countrySpinner: Spinner by lazy { findViewById(R.id.countrySpinner) }
    val provinceSpinner: Spinner by lazy { findViewById(R.id.provinceSpinner) }
    var provinceAdapter: ProvinceStateAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countrySpinner.onItemSelectedListener = this

        val provinceList = mutableListOf<ProvinceState>()
        provinceList.add(ProvinceState("Alberta","AB"))
        provinceList.add(ProvinceState("British Columbia","BC"))
        provinceList.add(ProvinceState("Saskatchewan","SK"))
        provinceList.add(ProvinceState("Manitoba","MB"))

        provinceAdapter = ProvinceStateAdapter(provinceList)
        provinceSpinner.adapter = provinceAdapter

    }

    fun onSubmitButtonClick(view: View) {
        val selectedCountry : String = countrySpinner.selectedItem as String
        val selectedProvinceState: ProvinceState = provinceSpinner.selectedItem as ProvinceState

        Toast.makeText(this,"Country: ${selectedCountry}, Province: ${selectedProvinceState.name}", Toast.LENGTH_LONG).show()
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