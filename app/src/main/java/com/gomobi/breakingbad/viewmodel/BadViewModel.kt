package com.gomobi.breakingbad.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gomobi.breakingbad.api.ApiClient
import com.gomobi.breakingbad.model.BadCharacter
import com.gomobi.breakingbad.model.CharacterFilter
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

/**
 * Project Name: BreakingBad
 * Author: David White
 * Email: gomobile.dw@gmail.com
 * Date: 19/11/2020
 */


class BadViewModel(application: Application): AndroidViewModel(application) {

    private val client = ApiClient.getInstance()

    var characterList = MutableLiveData<ArrayList<BadCharacter>>()
    var apiError = MutableLiveData<Exception>().apply { value = null }

    fun loadCharacters() {
        viewModelScope.launch {
            try {
                if (client.characters == null) {
                    client.characters = client.getCharacters()
                    characterList.apply { value = client.characters }
                } else {
                    characterList.apply { value = client.characters }
                }
            } catch (e: Exception) {
                Timber.d(e.localizedMessage)
                apiError.apply { value = e }
            }
        }
    }

    fun filter(filter: CharacterFilter) {
        filter.name = filter.name.toUpperCase(Locale.UK)
        val filteredList = ArrayList<BadCharacter>()
        if (client.characters != null) {
            for (ch in client.characters!!) {
                if (ch.compliesWith(filter)) {
                    filteredList.add(ch)
                }
            }
            characterList.apply { value = filteredList }
        }
    }

}