package com.cblanco.marvel.data.server.apiresults

import com.google.gson.annotations.SerializedName

data class GeneralApiResult<T>(
    @SerializedName("attributionHTML")
    val attributionHTML: String,
    @SerializedName("attributionText")
    val attributionText: String? = null,
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("copyright")
    val copyright: String? = null,
    @SerializedName("etag")
    val etag: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("data")
    val data: T? = null
)
