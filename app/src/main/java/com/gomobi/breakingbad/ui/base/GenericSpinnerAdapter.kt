package com.gomobi.breakingbad.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

/**
 * Project Name: BreakingBad
 * Author: David White
 * Email: gomobile.dw@gmail.com
 * Date: 19/11/2020
 */


abstract class GenericSpinnerAdapter<T>(context: Context, textViewResourceId: Int, _items: List<T>):
        ArrayAdapter<T>(context, textViewResourceId, _items) {

    val selectedItem: T?
        get() = items[selectedIndex]

    private var selectedIndex = 0

    private val items = _items

    override fun getCount(): Int = items.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItem(position: Int): T = items[position]

    private val inflater = LayoutInflater.from(context)

    abstract fun layoutId(): Int

    abstract fun dropDownLayoutId(): Int

    abstract fun selectedItem(): T?

    private fun inflateView(parent: ViewGroup, layoutId: Int): View {
        return inflater.inflate(layoutId, parent, false)
    }

    fun itemView(parent: ViewGroup): View {
        return inflateView(parent, layoutId())
    }

    fun dropDownView(parent: ViewGroup): View {
        return inflateView(parent, dropDownLayoutId())
    }


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomDropdownView(position, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, parent)
    }

    abstract fun getCustomView(position: Int, parent: ViewGroup): View

    abstract fun getCustomDropdownView(position: Int, parent: ViewGroup): View
}