package com.echung93.searchapp.presentation.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.echung93.andoridtest.viewmodel.SearchViewModel

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchUiState by viewModel.searchState.collectAsStateWithLifecycle()
    val searchList by viewModel.searchList.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.onRefresh()
    }

    SearchScreen(
        searchUiState = searchUiState,
        searchList = searchList,
        onQuery = { query -> viewModel.onQuery(query) },
        onError = {},
        onNextPage = { page -> viewModel.onNextPage(page)},
        onFavoriteClicked = { kakaoSearchData->
            viewModel.toggleFavorite(kakaoSearchData)
        },
        onClose = { viewModel.onClose()}
    )
}