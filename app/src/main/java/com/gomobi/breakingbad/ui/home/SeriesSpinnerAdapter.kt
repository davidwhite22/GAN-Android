package com.gomobi.breakingbad.ui.home

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gomobi.breakingbad.R
import com.gomobi.breakingbad.ui.base.GenericSpinnerAdapter

/**
 * Project Name: BreakingBad
 * Author: David White
 * Email: gomobile.dw@gmail.com
 * Date: 19/11/2020
 */


class SeriesSpinnerAdapter(context: Context, series: List<String>):
    GenericSpinnerAdapter<String>(context, itemLayout, series) {

    override fun layoutId(): Int = itemLayout

    override fun dropDownLayoutId(): Int = dropdownLayout

    override fun selectedItem(): String? = selectedItem

    private fun standardView(position: Int, parent: ViewGroup, isDropDown: Boolean): View {
        val view = if (isDropDown) dropDownView(parent) else itemView(parent)
        val tvSeries = view.findViewById<TextView>(R.id.tvSeries)
        tvSeries.text = getItem(position)
        return view
    }

    override fun getCustomView(position: Int, parent: ViewGroup): View = standardView(position, parent, false)

    override fun getCustomDropdownView(position: Int, parent: ViewGroup): View = standardView(position, parent, true)

    companion object {
        private const val itemLayout = R.layout.spinner_series_item
        private const val dropdownLayout = R.layout.spinner_series_dropdown_item

    }
}