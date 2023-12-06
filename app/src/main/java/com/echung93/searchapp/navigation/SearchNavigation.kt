package com.echung93.searchapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.echung93.searchapp.presentation.search.SearchRoute

const val SearchNavigationRoute = "search_route"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(SearchNavigationRoute, navOptions)
}

fun NavGraphBuilder.SearchScreen(

) {
    composable(
        route = SearchNavigationRoute
    ) {
        SearchRoute()
    }
}