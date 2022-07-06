package com.cblanco.data.source

import com.cblanco.domain.Character

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveCharacter(characters: List<Character>)
    suspend fun getPublicCharacters(): List<Character>
    suspend fun findById(id: Int): Character
}