package com.cblanco.data.source

import com.cblanco.domain.Character

interface RemoteDataSource {
    suspend fun getPublicCharacter(apiKey: String): List<Character>
}