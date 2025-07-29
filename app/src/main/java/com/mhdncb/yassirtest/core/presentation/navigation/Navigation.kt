package com.mhdncb.yassirtest.core.presentation.navigation

import android.R.id.home
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mhdncb.yassirtest.core.presentation.navigation.utils.Screen
import com.mhdncb.yassirtest.core.presentation.navigation.utils.navGraphDestination.characterDetails
import com.mhdncb.yassirtest.core.presentation.navigation.utils.navGraphDestination.home
import com.mhdncb.yassirtest.core.presentation.navigation.utils.navigateSingleTop

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val destination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        containerColor = Color.White
    ) { paddingValues ->
        Box {
            YassirNavHost(
                navController = navController,
                paddingValues = paddingValues
            )
        }
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