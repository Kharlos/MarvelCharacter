package com.cblanco.marvel.data.server.apiresults.publiccharacter

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)