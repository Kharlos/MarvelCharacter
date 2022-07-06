package com.cblanco.usecases

import com.cblanco.data.repository.CharacterRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FindCharacterByIdUseCaseTest {

    @Mock
    lateinit var characterRepository: CharacterRepository

    lateinit var findCharacterByIdUseCase: FindCharacterByIdUseCase

    @Before
    fun setUp() {
        findCharacterByIdUseCase = FindCharacterByIdUseCase(characterRepository)
    }

    @Test
    fun `test find character by id`() {
        runTest {

            val character = mockedCharacter.copy(id = 1)

            whenever(characterRepository.findById(1)).thenReturn(character)

            val result = findCharacterByIdUseCase.invoke(1)

            assertEquals(character, result)
        }
    }
}