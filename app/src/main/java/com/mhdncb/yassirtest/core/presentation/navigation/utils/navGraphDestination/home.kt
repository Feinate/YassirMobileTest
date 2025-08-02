package com.mhdncb.yassirtest.core.presentation.navigation.utils.navGraphDestination

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mhdncb.yassirtest.core.presentation.navigation.utils.Screen
import com.mhdncb.yassirtest.feature_main.presentation.screen.home.Home

fun NavGraphBuilder.home(
    paddingValues: PaddingValues,
    toCharacterDetails: (id: Int) -> Unit
) {
    composable<Screen.Home> {
        Home(
            paddingValues = paddingValues,
            toCharacterDetails = toCharacterDetails
        )
    }
}