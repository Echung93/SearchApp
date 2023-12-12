package com.echung93.searchapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.echung93.searchapp.model.KakaoSearchData
import com.echung93.searchapp.presentation.component.LoadingScreen
import com.echung93.searchapp.presentation.search.component.CustomSearchBar
import com.echung93.searchapp.presentation.search.component.EmptySearchResult
import com.echung93.searchapp.presentation.search.component.SearchResult
import com.echung93.searchapp.viewmodel.SearchUiState

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    query: String = "",
    closeButtonVisible: Boolean,
    searchUiState: SearchUiState,
    searchList: SearchResultState,
    onQuery: (String) -> Unit,
    onError: (String) -> Unit,
    onNextPage: (Int) -> Unit,
    onFavoriteClicked: (KakaoSearchData) -> Unit,
    onSearchQueryChanged: (String) -> Unit = {},
    onCloseButtonVisibleChanged: (Boolean) -> Unit = {},
    onClose: () -> Unit
) {

    val queryIsEmpty = stringResource(id = com.echung93.searchapp.R.string.query_is_empty)
    val uiState = remember(searchUiState) { mutableStateOf(searchUiState) }

    val onSearchEvent = {
        if (query.isEmpty()) {
            onError(queryIsEmpty)
        } else {
            onQuery(query)
            onCloseButtonVisibleChanged(true)
        }
    }

    val onCloseEvent = {
        onCloseButtonVisibleChanged(false)
        onClose()
    }

    Column(
        modifier = modifier.fillMaxSize()
    )
    {
        CustomSearchBar(
            active = false,
            query = query,
            onQueryChange = { value -> onSearchQueryChanged(value) },
            onCloseClicked = onCloseEvent,
            onSearchClicked = onSearchEvent,
            closeButtonVisible = closeButtonVisible
        )

        when (uiState.value) {
            SearchUiState.LOADING -> {
                LoadingScreen()
            }
            SearchUiState.RESULT -> {
                SearchResult(
                    searchList = searchList,
                    onFavoriteClicked = onFavoriteClicked,
                    onNextPage = { page ->
                        onNextPage(page)
                    })
            }
            SearchUiState.EMPTY -> {
                EmptySearchResult()
            }
            else -> {

            }
        }
    }
}