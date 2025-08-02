package com.mhdncb.yassirtest.core.presentation.navigation.utils.navGraphDestination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mhdncb.yassirtest.core.presentation.navigation.utils.Screen
import com.mhdncb.yassirtest.feature_main.presentation.screen.characterDetails.CharacterDetails

fun NavGraphBuilder.characterDetails(
    onQuit: () -> Unit
) {
    composable<Screen.CharacterDetails> {
        val character = it.toRoute<Screen.CharacterDetails>()
        CharacterDetails(
            name = character.name,
            image = character.image,
            species = character.species,
            status = character.status,
            onQuit = onQuit
        )
    }
}