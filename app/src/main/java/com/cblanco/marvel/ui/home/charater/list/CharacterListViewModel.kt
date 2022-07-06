package com.cblanco.marvel.ui.home.charater.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cblanco.domain.Character
import com.cblanco.marvel.ui.home.charater.common.ScopedViewModel
import com.cblanco.usecases.GetPublicCharactersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val getPublicCharactersUseCase: GetPublicCharactersUseCase,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private lateinit var currentCharacterList: List<Character>
    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> = _model

    sealed class UiModel {
        object Loading : UiModel()
        data class Content(val character: List<Character>) : UiModel()
        data class Navigation(val character: Character) : UiModel()
        object Error : UiModel()
    }

    init {
        initScope()
        getPublicCharacters()
    }

    fun getPublicCharacters() {
        launch() {
            _model.value = UiModel.Loading

            getPublicCharactersUseCase.invoke()
                .onEach {
                    this@CharacterListViewModel.currentCharacterList = it
                    _model.value = UiModel.Content(currentCharacterList)
                }
                .catch {
                    _model.value = UiModel.Error
                }
                .collect()

        }
    }

    fun onCharacterClick(character: Character) {
        _model.value = UiModel.Navigation(character)
        _model.value = UiModel.Content(currentCharacterList)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

}