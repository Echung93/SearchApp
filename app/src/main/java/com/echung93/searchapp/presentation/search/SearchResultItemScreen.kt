package com.echung93.searchapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.echung93.searchapp.domain.search.SearchData
import com.echung93.searchapp.domain.search.SearchState

@Composable
fun SearchResultItemScreen(
    modifier: Modifier,
    state: SearchState,
    listState: LazyListState = rememberLazyListState(),
) {
    state.searchInfo?.itemList?.let { data ->
        LazyColumn(
            modifier = modifier,
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                count = data.size,
                key = { index -> index },
            ) { index ->
                val item = data[index]

                ItemCompatCard(modifier, item)
            }
        }
    }
}

@Composable
fun ItemCompatCard(
    modifier: Modifier = Modifier,
    itemData: SearchData,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(0.dp),
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            AsyncImage(
                model = itemData.thumbnail_url,
                contentDescription = "Translated description of what the image contains"
            )

            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = maxWidth)
                    .padding(16.dp),
            ) {

                Text(
                    text = itemData.title,
                    modifier = Modifier
                        .height(5.dp),
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = itemData.dateTime.toString(),
                    modifier = Modifier
                        .wrapContentHeight(),
                    style = MaterialTheme.typography.body2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

        }
    }
}
