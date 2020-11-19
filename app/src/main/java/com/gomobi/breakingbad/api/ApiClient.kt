package com.gomobi.breakingbad.api

import com.gomobi.breakingbad.model.BadCharacter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Project Name: BreakingBad
 * Author: David White
 * Email: gomobile.dw@gmail.com
 * Date: 19/11/2020
 */


class ApiClient {
    private val service: BadApi
    var characters: ArrayList<BadCharacter>? = null

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ENDPOINT)
            .build()
        service = retrofit.create(BadApi::class.java)
    }

    suspend fun getCharacters() = service.getCharacters()

    companion object {
        @Volatile
        private var INSTANCE: ApiClient? = null

        private const val ENDPOINT = "https://breakingbadapi.com/api/"

        fun getInstance(): ApiClient {
            return INSTANCE?: synchronized(this) {
                val instance = ApiClient()
                INSTANCE = instance
                instance
            }

        }
    }
}