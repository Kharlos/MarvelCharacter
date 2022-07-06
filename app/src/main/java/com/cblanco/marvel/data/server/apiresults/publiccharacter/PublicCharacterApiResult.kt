package com.cblanco.marvel.data.server.apiresults.publiccharacter

import com.google.gson.annotations.SerializedName

class PublicCharacterApiResult(
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val results: List<ResultCharacter>,
    @SerializedName("total")
    val total: Int
)