package com.echung93.searchapp.presentation.favorite.component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.echung93.searchapp.model.KakaoSearchData

@Composable
fun FavoriteResult(
    dataList: List<KakaoSearchData>,
    onFavoriteClicked: (KakaoSearchData) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxHeight(),
        columns = GridCells.Fixed(3)
    ) {
        items(
            count = dataList.size,
            key = { index -> index }
        ) { index ->
            val item = dataList[index]

            FavoriteResultItem(searchData = item, onFavoriteClicked = onFavoriteClicked)
        }
    }
}