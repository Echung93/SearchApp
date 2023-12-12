package com.echung93.searchapp.presentation.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.echung93.searchapp.viewmodel.SearchViewModel

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
) {
    val searchUiState by viewModel.searchState.collectAsStateWithLifecycle()
    val searchList by viewModel.searchList.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val closeButtonVisible by viewModel.closeButtonVisible.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.onRefresh()
    }

    SearchScreen(
        query = searchQuery,
        closeButtonVisible = closeButtonVisible,
        searchUiState = searchUiState,
        searchList = searchList,
        onQuery = { query -> viewModel.onQuery(query) },
        onError = showSnackbar,
        onNextPage = { page -> viewModel.onNextPage(page)},
        onFavoriteClicked = { kakaoSearchData->
            viewModel.toggleFavorite(kakaoSearchData)
        },
        onClose = viewModel::onClose,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onCloseButtonVisibleChanged = viewModel:: onCloseButtonVisibleChanged
    )
}
