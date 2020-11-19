package com.gomobi.breakingbad.ui.home

import android.view.View
import android.widget.AdapterView

/**
 * Project Name: BreakingBad
 * Author: David White
 * Email: gomobile.dw@gmail.com
 * Date: 19/11/2020
 */


class SpinnerSelectedListener(initialItem: Int, listener: OnSpinnerItemSelected?): AdapterView.OnItemSelectedListener {
    private var initialIndex = initialItem
    private var onSpinnerItemSelected: OnSpinnerItemSelected? = listener

    interface OnSpinnerItemSelected {
        fun onSelectedItem(position: Int)
    }

    override fun onItemSelected(adapter: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        if (initialIndex == pos) {
            return
        }
        onSpinnerItemSelected?.onSelectedItem(pos)
        initialIndex = pos

    }

    override fun onNothingSelected(adapter: AdapterView<*>?) {
    }
}