package com.cblanco.marvel.data.server.apiresults.publiccharacter

import com.cblanco.marvel.data.server.apiresults.publiccharacter.Item

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)