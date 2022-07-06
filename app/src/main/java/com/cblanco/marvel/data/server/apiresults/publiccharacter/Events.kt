package com.cblanco.marvel.data.server.apiresults.publiccharacter

import com.cblanco.marvel.data.server.apiresults.publiccharacter.ItemX

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)