package com.cblanco.marvel.data.server.apiresults.publiccharacter

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)