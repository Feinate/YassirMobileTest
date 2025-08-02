package com.mhdncb.yassirtest.feature_main.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mhdncb.yassirtest.core.domain.repository.CharactersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

data class HomeUiState(
    val query: String = ""
)

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class HomeViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState()
        )

    val paging = _uiState
        .map { it.query }
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            charactersRepository.getCharactersPaging(query)
        }.cachedIn(viewModelScope)

    fun onSearchQueryChanged(value: String) {
        _uiState.update {
            it.copy(query = value)
        }
    }
}