package com.echung93.searchapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.echung93.searchapp.presentation.favorite.FavoriteRoute

const val FavoriteNavigationRoute = "favorite_route"

fun NavController.navigateToFavorite(navOptions: NavOptions? = null) {
    this.navigate(FavoriteNavigationRoute, navOptions)
}

fun NavGraphBuilder.FavoriteScreen() {
    composable(
        route = FavoriteNavigationRoute
    ) {
        FavoriteRoute()
    }
}