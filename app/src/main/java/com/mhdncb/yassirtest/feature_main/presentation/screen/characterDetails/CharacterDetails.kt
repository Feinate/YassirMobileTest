package com.mhdncb.yassirtest.feature_main.presentation.screen.characterDetails

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterDetails(
    onQuit: () -> Unit,
    viewModel: CharacterDetailsViewModel = koinViewModel()
) {
}