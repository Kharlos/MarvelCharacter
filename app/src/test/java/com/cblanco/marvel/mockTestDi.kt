package com.cblanco.marvel

import com.cblanco.data.source.LocalDataSource
import com.cblanco.data.source.RemoteDataSource
import com.cblanco.domain.Character
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun initMockedDi(vararg modules: Module) {
    startKoin {
        modules(listOf(mockedAppModule, dataModule) + modules)
    }
}

private val mockedAppModule = module {
    single(named("apiKey")) { "12456" }
    single<LocalDataSource> { FakeLocalDataSource() }
    single<RemoteDataSource> { FakeRemoteDataSource() }
    single { Dispatchers.Unconfined }
}

val mockedCharacter = Character(
    1,
    "Mocked Character",
    "",
    ""
)

val mockedCharacters = listOf(
    mockedCharacter.copy(1),
    mockedCharacter.copy(2),
    mockedCharacter.copy(3),
    mockedCharacter.copy(4)
)

class FakeLocalDataSource : LocalDataSource {

    var characters: List<Character> = emptyList()

    override suspend fun isEmpty() = characters.isEmpty()

    override suspend fun saveCharacter(characters: List<Character>) {
        this.characters = characters
    }

    override suspend fun getPublicCharacters(): List<Character> = characters

    override suspend fun findById(id: Int): Character = characters.first { it.id == id }

}

class FakeRemoteDataSource : RemoteDataSource {

    var characters = mockedCharacters

    override suspend fun getPublicCharacter(apiKey: String) = characters
}
