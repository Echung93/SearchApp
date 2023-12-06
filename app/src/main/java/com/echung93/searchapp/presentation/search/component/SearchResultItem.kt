package com.echung93.searchapp.presentation.search.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.echung93.searchapp.designsystem.icon.AppIcons
import com.echung93.searchapp.model.KakaoSearchData
import java.text.SimpleDateFormat
import java.util.*
import com.echung93.searchapp.R
import com.echung93.searchapp.model.SearchData

@Composable
fun SearchResultItem(
    item: KakaoSearchData,
    onFavoriteClicked: (KakaoSearchData) -> Unit
) {

    val formatter =
        SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val dateText = formatter.format(item.data.dateTime)


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth(),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(item.data.image_url)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = item.data.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 100.dp)
                    .padding(16.dp),
            ) {
                Text(
                    text = item.data.title,
                    modifier = Modifier
                        .wrapContentHeight(),
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = dateText,
                    modifier = Modifier
                        .wrapContentHeight(),
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector =
                    if (item.isFavorite) AppIcons.Favorite else AppIcons.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.favorite),
                    modifier = Modifier
                        .size(36.dp)
                        .clip(
                            shape = CircleShape
                        )
                        .clickable(
                            onClick = {
                                onFavoriteClicked(item)
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
fun SearchResultItemPreview() {
    SearchResultItem(
        item =
        KakaoSearchData(
            isFavorite = false,
            SearchData(
                title = "티스토리",
                thumbnail_url = "https://search2.kakaocdn.net/argon/130x130_85_c/H7tUIHSP4nf",
                image_url = "https://blog.kakaocdn.net/dn/2hAIW/btsvjd963vO/PJkYwNgtVRotRtmvRJqwX0/img.jpg",
                doc_url = "https://cutepuppy.tistory.com/19",
                dateTime = Date()
            )
        ), onFavoriteClicked = {}
    )
}
