package com.echung93.searchapp.presentation.search.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.echung93.searchapp.model.KakaoSearchData
import com.echung93.searchapp.presentation.search.SearchResultState
import com.echung93.searchapp.R

@Composable
fun SearchResult(searchList: SearchResultState,
                 onNextPage: (page: Int) -> Unit,
                 onFavoriteClicked: (KakaoSearchData) -> Unit
) {
    val state = rememberLazyListState()

    val isPageable by derivedStateOf {
        ((state.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            ?: 0) >= searchList.searchResult.size - 1) && searchList.pageable
    }

    LaunchedEffect(key1 = isPageable) {
        if (isPageable) {
            onNextPage(searchList.page + 1)
        }
    }

    LazyColumn(state = state) {
        items(
            count = searchList.searchResult.size,
            key = { index -> index }
        ) { index ->
            val searchResult = searchList.searchResult[index]

            SearchResultItem(
                item = searchResult,
                onFavoriteClicked = onFavoriteClicked
            )
        }

        item {
            if(!searchList.pageable) {
                SearchLastPage()
            }
        }
    }
}

@Composable
fun SearchLastPage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(stringResource(id = R.string.last_page_result))
    }
}

@Preview
@Composable
fun SearchLastPagePreview() {
    SearchLastPage()
}
