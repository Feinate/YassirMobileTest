package com.mhdncb.yassirtest.core.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mhdncb.yassirtest.core.presentation.navigation.utils.Screen
import com.mhdncb.yassirtest.core.presentation.navigation.utils.navGraphDestination.characterDetails
import com.mhdncb.yassirtest.core.presentation.navigation.utils.navGraphDestination.home
import com.mhdncb.yassirtest.core.presentation.navigation.utils.navigateSingleTop

@Composable
fun Navigation() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { paddingValues ->
        YassirNavHost(
            navController = navController,
            paddingValues = paddingValues
        )
    }
}

@Composable
private fun YassirNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home
    ) {
        // Main Screens
        home(
            paddingValues = paddingValues,
            toCharacterDetails = { characterId ->
                navController.navigateSingleTop(Screen.CharacterDetails)
            }
        )

        // Others

        characterDetails(
            onQuit = navController::navigateUp
        )
    }
}