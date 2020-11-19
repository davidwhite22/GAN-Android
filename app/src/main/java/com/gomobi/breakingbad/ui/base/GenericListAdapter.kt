package com.gomobi.breakingbad.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Project Name: BreakingBad
 * Author: David White
 * Email: gomobile.dw@gmail.com
 * Date: 19/11/2020
 */


abstract class GenericListAdapter<T, VH: GenericListViewHolder<T>>(context: Context): RecyclerView.Adapter<VH>() {
    private var items = ArrayList<T>()

    open val inflater: LayoutInflater = LayoutInflater.from(context)

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH


    fun <T1> createHolder(
        viewGroup: ViewGroup,
        layoutRes: Int,
        method: (View) -> T1
    ): T1 {
        val view = inflater.inflate(layoutRes, viewGroup, false)
        return method(view) // Creates T(view).
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getListItems() = items

    fun itemAt(position: Int): T = items[position]

    open fun setListItems(items: ArrayList<T>) {
        this.items = items
        notifyDataSetChanged()
    }

}