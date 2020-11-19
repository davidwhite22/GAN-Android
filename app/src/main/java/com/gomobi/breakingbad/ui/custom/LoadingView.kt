package com.gomobi.breakingbad.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.gomobi.breakingbad.R

/**
 * Project Name: BreakingBad
 * Author: David White
 * Email: gomobile.dw@gmail.com
 * Date: 19/11/2020
 */


class LoadingView: RelativeLayout {
    constructor(context: Context?) : super(context) {
        inflate(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        inflate(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inflate(context)
    }

    private fun inflate(context: Context?) {
        LayoutInflater.from(context).inflate(R.layout.view_loading, this, true)
    }

    fun show() {
        visibility = View.VISIBLE
    }

    fun hide() {
        visibility = View.INVISIBLE
    }
}