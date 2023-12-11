package com.echung93.searchapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.echung93.andoridtest.viewmodel.SearchUiState
import com.echung93.searchapp.model.KakaoSearchData
import com.echung93.searchapp.presentation.component.LoadingScreen
import com.echung93.searchapp.presentation.search.component.CustomSearchBar
import com.echung93.searchapp.presentation.search.component.EmptySearchResult
import com.echung93.searchapp.presentation.search.component.SearchResult

@Composable
fun SearchScreen(
    searchUiState: SearchUiState,
    searchList: SearchResultState,
    onQuery: (String) -> Unit,
    onError: (String) -> Unit,
    onNextPage: (Int) -> Unit,
    onFavoriteClicked: (KakaoSearchData) -> Unit,
    onClose: () -> Unit
) {
    // remember은 구성 변경 전반에서 상태 유지가 안되서 Bundle에 저장하는 rememberSaveable 사용
    var query by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    val queryIsEmpty =
        stringResource(id = com.echung93.searchapp.R.string.query_is_empty)
    val uiState = remember(searchUiState) {
        mutableStateOf(searchUiState)
    }

    val onSearchEvent = {
        if (query.isEmpty()) {
            onError(queryIsEmpty)
        } else {
            onQuery(query)
            active = true
        }
    }

    val onActiveChangeEvent = {
        active = false
        onClose()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    )
    {
        CustomSearchBar(
            active = active,
            query = query,
            onQuery = { onSearchEvent() },
            onQueryChange = { value ->
                query = value
            },
            onActiveChange = { onActiveChangeEvent() }
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