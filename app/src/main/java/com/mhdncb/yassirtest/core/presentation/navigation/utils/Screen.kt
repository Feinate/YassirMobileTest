package com.mhdncb.yassirtest.core.presentation.navigation.utils

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen() {

    // Main

    @Serializable
    internal data object Home : Screen()

    @Serializable
    internal data class CharacterDetails(
        val id: String,
    ): Screen()
}