package com.echung93.searchapp.presentation.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.echung93.searchapp.viewmodel.FavoriteViewModel

@Composable
fun FavoriteRoute(
    viewModel: FavoriteViewModel = hiltViewModel(),
) {
    val favoriteUiState by viewModel.favoriteUiState.collectAsStateWithLifecycle()
    val favoriteList by viewModel.favoriteList.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.getFavoriteList()
    }

    FavoriteScreen(
        favoriteList = favoriteList,
        favoriteUiState = favoriteUiState,
        onFavoriteClicked = { kakaoSearchData ->
            viewModel.deleteFavorite(kakaoSearchData)
        })
}
