package com.cblanco.data.repository

import com.cblanco.data.source.LocalDataSource
import com.cblanco.data.source.RemoteDataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CharacterRepositoryTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    lateinit var characterRepository: CharacterRepository

    private val apiKey = "apikeyMocked123"

    @Before
    fun setUp() {
        characterRepository =
            CharacterRepository(localDataSource, remoteDataSource, apiKey)
    }

    @Test
    fun `getPublicCharacters gets from local data source first`() {
        runTest {

            val localCharacters = listOf(mockedCharacter.copy(1))
            whenever(localDataSource.isEmpty()).thenReturn(false)
            whenever(localDataSource.getPublicCharacters()).thenReturn(localCharacters)

            characterRepository.getPublicCharacters().onEach {
                assertEquals(localCharacters, it)

            }.collect()

        }
    }

    @Test
    fun `getPublicCharacters saves remote data to local`() {
        runTest {

            val remoteCharacters = listOf(mockedCharacter.copy(2))
            whenever(localDataSource.isEmpty()).thenReturn(true)
            whenever(remoteDataSource.getPublicCharacter(any())).thenReturn(remoteCharacters)

            characterRepository.getPublicCharacters().onEach {
                verify(localDataSource).saveCharacter(remoteCharacters)
            }.collect()

        }
    }

    @Test
    fun `findById calls local data source`() {
        runTest {

            val character = mockedCharacter.copy(id = 5)
            whenever(localDataSource.findById(5)).thenReturn(character)

            val result = characterRepository.findById(5)

            assertEquals(character, result)
        }
    }

}