package com.echung93.searchapp.presentation.favorite.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.echung93.searchapp.designsystem.icon.AppIcons
import com.echung93.searchapp.model.KakaoSearchData
import com.echung93.searchapp.model.SearchData
import java.util.*

@Composable
fun FavoriteResultItem(
    modifier: Modifier = Modifier,
    searchData: KakaoSearchData,
    onFavoriteClicked: (KakaoSearchData) -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = RectangleShape,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            AsyncImage(
                model = searchData.data.thumbnail_url,
                contentDescription = "Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Row(
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = if (searchData.isFavorite) {
                        AppIcons.Favorite
                    } else {
                        AppIcons.FavoriteBorder
                    },
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(
                            shape = CircleShape
                        )
                        .clickable(
                            onClick = {
                                onFavoriteClicked(searchData)
                            },
                        )
                        .padding(6.dp)
                )
            }

        }
    }
}

@Composable
@Preview
fun PreviewFavoriteResultItem() {
    FavoriteResultItem(
        searchData = KakaoSearchData(
            isFavorite = true,
            SearchData(
                title = "티스토리",
                thumbnail_url = "https://search2.kakaocdn.net/argon/130x130_85_c/H7tUIHSP4nf",
                image_url = "https://blog.kakaocdn.net/dn/2hAIW/btsvjd963vO/PJkYwNgtVRotRtmvRJqwX0/img.jpg",
                doc_url = "https://cutepuppy.tistory.com/19",
                dateTime = Date()
            )
        ),
        onFavoriteClicked = {})
}
