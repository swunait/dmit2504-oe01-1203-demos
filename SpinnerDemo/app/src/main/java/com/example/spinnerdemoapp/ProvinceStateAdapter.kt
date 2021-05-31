package com.example.spinnerdemoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ProvinceStateAdapter(val provinceList: List<ProvinceState>) : BaseAdapter() {

//    val provinceList = mutableListOf<ProvinceState>()
//
//    fun addItem(newItem: ProvinceState) {
//        provinceList.add(newItem)
//        notifyDataSetChanged()
//    }

    override fun getCount(): Int {
        return provinceList.size
    }

    override fun getItem(position: Int): Any {
        return provinceList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(parent?.context)
        val inflatedView = inflater.inflate(R.layout.spinner_item_province, parent, false)

        val provinceNameTextView = inflatedView.findViewById<TextView>(R.id.spinner_item_province_name)
        provinceNameTextView.text = provinceList[position].name

        return inflatedView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        return super.getDropDownView(position, convertView, parent)
        val inflater = LayoutInflater.from(parent?.context)
        val inflatedView = inflater.inflate(R.layout.spinner_dropdown_item_province, parent, false)

        val provinceNameTextView = inflatedView.findViewById<TextView>(R.id.spinner_dropdown_item_province_name)
        val provinceAbbrevTextView = inflatedView.findViewById<TextView>(R.id.spinner_dropdown_item_province_abbreviation)

        val currentProvince = provinceList[position]
        provinceNameTextView.text = currentProvince.name
        provinceAbbrevTextView.text = currentProvince.abbreviation

        return inflatedView
    }
}