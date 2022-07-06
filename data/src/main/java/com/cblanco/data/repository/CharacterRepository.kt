package com.cblanco.data.repository

import com.cblanco.data.source.LocalDataSource
import com.cblanco.data.source.RemoteDataSource
import com.cblanco.domain.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val apiKey: String
) {

    suspend fun getPublicCharacters(): Flow<List<Character>> = flow {

        if (localDataSource.isEmpty()) {
            val characters = remoteDataSource.getPublicCharacter(apiKey)
            localDataSource.saveCharacter(characters)
        }

        emit(localDataSource.getPublicCharacters())
    }

    suspend fun findById(id: Int): Character = localDataSource.findById(id)

}