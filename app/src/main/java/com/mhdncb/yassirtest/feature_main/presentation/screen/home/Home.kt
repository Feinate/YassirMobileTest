package com.mhdncb.yassirtest.feature_main.presentation.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun Home(
    paddingValues: PaddingValues,
    toCharacterDetails: (id: String) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
}