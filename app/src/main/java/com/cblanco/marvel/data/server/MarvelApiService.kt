package com.cblanco.marvel.data.server

import com.cblanco.marvel.data.server.apiresults.GeneralApiResult
import com.cblanco.marvel.data.server.apiresults.publiccharacter.PublicCharacterApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {

    @GET("public/characters")
    suspend fun listPublicCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("apikey") apiKey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String
    ): GeneralApiResult<PublicCharacterApiResult>


}