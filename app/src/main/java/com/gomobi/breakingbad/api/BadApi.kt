package com.gomobi.breakingbad.api

import com.gomobi.breakingbad.model.BadCharacter
import retrofit2.http.GET

/**
 * Project Name: BreakingBad
 * Author: David White
 * Email: gomobile.dw@gmail.com
 * Date: 19/11/2020
 */

interface BadApi {

    @GET("characters")
    suspend fun getCharacters(): ArrayList<BadCharacter>
}