package com.mhdncb.yassirtest.core.presentation.navigation.utils.navGraphDestination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mhdncb.yassirtest.core.presentation.navigation.utils.Screen
import com.mhdncb.yassirtest.feature_main.presentation.screen.characterDetails.CharacterDetails

fun NavGraphBuilder.characterDetails(
    onQuit: () -> Unit
) {
    composable<Screen.CharacterDetails> {
        CharacterDetails(
            onQuit = onQuit
        )
    }
}