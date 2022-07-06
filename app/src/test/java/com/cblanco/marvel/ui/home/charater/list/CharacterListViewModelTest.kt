package com.cblanco.marvel.ui.home.charater.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cblanco.marvel.mockedCharacter
import com.cblanco.usecases.GetPublicCharactersUseCase
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterListViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Mock
    lateinit var getPublicCharactersUseCase: GetPublicCharactersUseCase
    @Mock
    lateinit var observer: Observer<CharacterListViewModel.UiModel>

    private lateinit var viewModel:CharacterListViewModel

    @Before
    fun setUp(){
        viewModel = CharacterListViewModel(getPublicCharactersUseCase, Dispatchers.Unconfined)
    }

    @Test
    fun `verify that loader is shown `() {
        runTest{

            viewModel.model.observeForever(observer)
            viewModel.getPublicCharacters()
            verify(observer).onChanged(CharacterListViewModel.UiModel.Loading)
        }
    }

    @Test
    fun `verify that getPublicCharacter is called`() {
        runTest{

            val characters = listOf(mockedCharacter.copy(1))
            whenever(getPublicCharactersUseCase.invoke()).thenReturn(flow { emit(characters) })

            viewModel.model.observeForever(observer)
            viewModel.getPublicCharacters()
            verify(observer).onChanged(CharacterListViewModel.UiModel.Content(characters))
        }
    }

}