package com.echung93.searchapp.presentation.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.echung93.searchapp.R
import com.echung93.searchapp.model.KakaoSearchData
import com.echung93.searchapp.presentation.component.LoadingScreen
import com.echung93.searchapp.presentation.favorite.component.EmptyFavoriteScreen
import com.echung93.searchapp.presentation.favorite.component.FavoriteResult
import com.echung93.searchapp.viewmodel.FavoriteUiState

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    favoriteUiState: FavoriteUiState,
    favoriteList: List<KakaoSearchData>,
    onFavoriteClicked: (KakaoSearchData) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.favorite_screen_title),
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(16.dp),
        )

        when (favoriteUiState) {
            FavoriteUiState.LOADING -> {
                LoadingScreen()
            }
            FavoriteUiState.SUCCESS -> {
                if (favoriteList.isEmpty()) {
                    EmptyFavoriteScreen()
                } else {
                    FavoriteResult(
                        dataList = favoriteList,
                        onFavoriteClicked = onFavoriteClicked
                    )
                }
            }
        }
    }
}
