package com.cblanco.usecases

import com.cblanco.data.repository.CharacterRepository
import com.cblanco.domain.Character
import kotlinx.coroutines.flow.Flow

class GetPublicCharactersUseCase(private val characterRepository:CharacterRepository) {
    suspend fun invoke(): Flow<List<Character>> = characterRepository.getPublicCharacters()
}