package com.gomobi.breakingbad.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Project Name: BreakingBad
 * Author: David White
 * Email: gomobile.dw@gmail.com
 * Date: 19/11/2020
 */

abstract class GenericListViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T, position: Int)
}