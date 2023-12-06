package com.echung93.searchapp.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.echung93.searchapp.R
import com.echung93.searchapp.designsystem.icon.AppIcons

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val route: String
) {
    Search(
        selectedIcon = AppIcons.Search,
        unselectedIcon = AppIcons.SearchBorder,
        iconTextId = R.string.Search,
        route = SearchNavigationRoute
    ),
    Favorite(
        selectedIcon = AppIcons.Favorite,
        unselectedIcon = AppIcons.FavoriteBorder,
        iconTextId = R.string.Favorite,
        route = FavoriteNavigationRoute
    )
}