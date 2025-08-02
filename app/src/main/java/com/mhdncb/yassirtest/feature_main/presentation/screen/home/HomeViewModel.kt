package com.mhdncb.yassirtest.feature_main.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mhdncb.yassirtest.core.domain.repository.CharactersRepository

class HomeViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    val paging = charactersRepository.getCharactersPaging().cachedIn(viewModelScope)
}