package com.mhdncb.yassirtest.core.presentation.navigation.utils

import androidx.navigation.NavController

fun <T : Any> NavController.navigateSingleTop(
    route: T,
    popUpTo: Screen? = null,
    popUpReset: Int? = null
) {
    this.navigate(route) {
        launchSingleTop = true
        popUpTo?.let {
            popUpTo(it)
        }
        popUpReset?.let {
            popUpTo(0)
        }
    }
}