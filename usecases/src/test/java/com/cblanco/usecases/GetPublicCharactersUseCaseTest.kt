package com.cblanco.usecases

import com.cblanco.data.repository.CharacterRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPublicCharactersUseCaseTest {

    @Mock
    lateinit var characterRepository: CharacterRepository

    lateinit var getPublicCharactersUseCase: GetPublicCharactersUseCase

    @Before
    fun setUp() {
        getPublicCharactersUseCase = GetPublicCharactersUseCase(characterRepository)
    }

    @Test
    fun `test invoke get public characters`() {
        runTest {

            val characters = listOf(mockedCharacter.copy(id = 1))

            whenever(characterRepository.getPublicCharacters()).thenReturn(flow { emit(characters) })

            characterRepository.getPublicCharacters().onEach {
                Assert.assertEquals(characters, it)
            }.collect()
        }
    }
}