package com.cblanco.usecases

import com.cblanco.data.repository.CharacterRepository
import com.cblanco.domain.Character

class FindCharacterByIdUseCase(private val characterRepository: CharacterRepository) {
    suspend fun invoke(id: Int): Character = characterRepository.findById(id)
}