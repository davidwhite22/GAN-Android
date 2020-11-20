package com.gomobi.breakingbad.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

/**
 * Project Name: BreakingBad
 * Author: David White
 * Email: gomobile.dw@gmail.com
 * Date: 19/11/2020
 */

@Parcelize
data class BadCharacter(
    val char_id: Int,
    val name: String,
    val birthday: String,
    val occupation: ArrayList<String>,
    val img: String,
    val status: String,
    val nickname: String,
    val appearance: List<Int>,
    val portrayed: String,
    val category: String,
    val better_call_saul_appearance: List<Int>
): Parcelable {

    fun makeOccupationString(): String {
        if (occupation.isNullOrEmpty()) {
            return ""
        }
        val stringBuilder = StringBuilder()
        for ((index, value) in occupation.withIndex()) {
            stringBuilder.append(value)
            if (index != occupation.size - 1) {
                stringBuilder.append("\n")
            }
        }
        return stringBuilder.toString()
    }

    fun makeApperancesString(): String {
        if (appearance.isNullOrEmpty()) {
            return ""
        }
        val stringBuilder = StringBuilder()
        for ((index, value) in appearance.withIndex()) {
            if (index != 0) {
                stringBuilder.append(" ")
            }
            stringBuilder.append(value)
            if (index != appearance.size - 1) {
                stringBuilder.append(",")
            }

        }
        return stringBuilder.toString()
    }

    fun compliesWith(filter: CharacterFilter): Boolean {
        val nameOk = if (filter.name.isEmpty()) true else name.toUpperCase(Locale.UK).startsWith(filter.name)
        val appOK = if (filter.seriesAppearance == 0) true else
            if (appearance.isNullOrEmpty()) false else appearance.contains(filter.seriesAppearance)
        return if (nameOk) appOK else false
    }
}
