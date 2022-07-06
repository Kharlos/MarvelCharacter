package com.cblanco.marvel

import android.app.Application
import com.cblanco.data.repository.CharacterRepository
import com.cblanco.data.source.LocalDataSource
import com.cblanco.data.source.RemoteDataSource
import com.cblanco.marvel.data.database.CharacterDatabase
import com.cblanco.marvel.data.database.RoomDataSource
import com.cblanco.marvel.data.server.MarvelApi
import com.cblanco.marvel.data.server.MarvelApi.Companion.API_VERSION
import com.cblanco.marvel.data.server.MarvelCharacterApiDataSource
import com.cblanco.marvel.ui.home.charater.detail.CharacterDetailFragment
import com.cblanco.marvel.ui.home.charater.detail.CharacterDetailViewModel
import com.cblanco.marvel.ui.home.charater.list.CharacterListFragment
import com.cblanco.marvel.ui.home.charater.list.CharacterListViewModel
import com.cblanco.usecases.FindCharacterByIdUseCase
import com.cblanco.usecases.GetPublicCharactersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module


fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single(named("apiKey")) { androidApplication().getString(R.string.api_key) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single(named("baseUrl")) { "https://gateway.marvel.com/$API_VERSION" }
    single { CharacterDatabase.build(get()) }
    single { MarvelApi(get(named("baseUrl"))) }
    factory<LocalDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { MarvelCharacterApiDataSource(get()) }
}

val dataModule = module {
    factory { CharacterRepository(get(), get(), get(named("apiKey"))) }

}

private val scopesModule = module {
    scope(named<CharacterListFragment>()) {
        viewModel { CharacterListViewModel(get(), get()) }
        scoped { GetPublicCharactersUseCase(get()) }
    }

    scope(named<CharacterDetailFragment>()) {
        viewModel { CharacterDetailViewModel(get(), get(), get()) }
        scoped { FindCharacterByIdUseCase(get()) }
    }


}