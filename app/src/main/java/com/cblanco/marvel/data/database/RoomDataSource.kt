package com.cblanco.marvel.data.database

import com.cblanco.data.source.LocalDataSource
import com.cblanco.domain.Character
import com.cblanco.marvel.data.mappers.toDomain
import com.cblanco.marvel.data.mappers.toRoomDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: CharacterDatabase) : LocalDataSource {

    private val characterDao = db.characterDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { characterDao.characterCount() <= 0 }

    override suspend fun saveCharacter(character: List<Character>) {
        withContext(Dispatchers.IO) { characterDao.insertCharacters(character.map { it.toRoomDb() }) }
    }

    override suspend fun getPublicCharacters(): List<Character> = withContext(Dispatchers.IO) {
        characterDao.getAll().map { it.toDomain() }
    }

    override suspend fun findById(id: Int): Character = withContext(Dispatchers.IO) {
        characterDao.findById(id).toDomain()
    }
}