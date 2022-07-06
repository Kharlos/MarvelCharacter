package com.cblanco.marvel.ui.home.charater.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cblanco.domain.Character
import com.cblanco.marvel.ui.home.charater.common.ScopedViewModel
import com.cblanco.usecases.FindCharacterByIdUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val characterId: Int,
    private val findCharacterByIdUseCase: FindCharacterByIdUseCase,
    override val uiDispatcher: CoroutineDispatcher
) :
    ScopedViewModel(uiDispatcher) {

    data class UiModel(val character: Character)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> = _model

    init {
        findCharacter()
    }

    private fun findCharacter() = launch {
        _model.value = UiModel(findCharacterByIdUseCase.invoke(characterId))
    }

}