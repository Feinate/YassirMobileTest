package com.mhdncb.yassirtest.feature_main.presentation.screen.characterDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

data class CharacterDetailsUiState(
    val isLoading: Boolean = false
)

class CharacterDetailsViewModel(

) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterDetailsUiState())
    val uiState = _uiState.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            initialValue = CharacterDetailsUiState(),
            started = SharingStarted.WhileSubscribed(5_000)
        )

}