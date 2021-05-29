package com.example.spinnerdemoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ProvinceStateAdapter : BaseAdapter() {

    val provinceList = mutableListOf<ProvinceState>()

    fun addItem(newItem: ProvinceState) {
        provinceList.add(newItem)
        notifyDataSetChanged()
    }

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
        val inflatedView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false)

        val text1TextView = inflatedView.findViewById<TextView>(android.R.id.text1)
        text1TextView.text = provinceList[position].name

        return inflatedView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return super.getDropDownView(position, convertView, parent)
    }
}