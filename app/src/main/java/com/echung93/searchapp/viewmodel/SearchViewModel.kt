package com.echung93.searchapp.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echung93.searchapp.domain.use_case.favorite.AddFavoriteDataUseCase
import com.echung93.searchapp.domain.use_case.favorite.DeleteFavoriteDataUseCase
import com.echung93.searchapp.domain.use_case.search.GetSearchDataUseCase
import com.echung93.searchapp.domain.util.Resource
import com.echung93.searchapp.model.KakaoSearchData
import com.echung93.searchapp.presentation.search.SearchResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SEARCH_QUERY = "searchQuery"
private const val CLOSE_BUTTON_VISIBLE = "closeButtonVisible"

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchDataUseCase: GetSearchDataUseCase,
    private val addFavoriteDataUseCase: AddFavoriteDataUseCase,
    private val deleteFavoriteDataUseCase: DeleteFavoriteDataUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")
    val closeButtonVisible = savedStateHandle.getStateFlow(key = CLOSE_BUTTON_VISIBLE, initialValue = false)

    private val _searchState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState.IDLE)
    val searchState: StateFlow<SearchUiState> = _searchState

    private val _searchList: MutableStateFlow<SearchResultState> =
        MutableStateFlow(
            SearchResultState(
                query = "",
                page = 1,
                pageable = false
            )
        )
    val searchList: StateFlow<SearchResultState> = _searchList

    fun onQuery(query: String) {
        _searchState.value = SearchUiState.LOADING

        if (query.isNotEmpty()) {
            loadData(query, 1)
        }
    }

    fun onNextPage(page: Int) {
        loadData(_searchList.value.query, page)
    }

    fun onRefresh() {
        val state = _searchList.value

        loadData(state.query, page = state.page, refresh = true)
    }

    private fun loadData(
        query: String,
        page: Int,
        refresh: Boolean = false
    ) {
        viewModelScope.launch {
            getSearchDataUseCase(query, page, refresh, _searchList.value).collectLatest {
                when(it) {
                    is Resource.Success -> {
                        if(it.data!!.searchResult.isEmpty()) {
                            _searchState.value = SearchUiState.EMPTY
                        } else {
                            _searchState.value = SearchUiState.RESULT
                            _searchList.value = it.data
                        }
                    }
                    is Resource.Idle -> {
                        _searchState.value = SearchUiState.IDLE
                    }
                    else -> {
                        _searchState.value = SearchUiState.ERROR
                    }
                }
            }
        }
    }

    fun toggleFavorite(kakaoSearchData: KakaoSearchData) {
        viewModelScope.launch(Dispatchers.IO) {
            if(kakaoSearchData.isFavorite) {
                deleteFavoriteDataUseCase(kakaoSearchData)
            } else {
                addFavoriteDataUseCase(kakaoSearchData)
            }

            _searchList.update { searchResultState ->
                searchResultState.copy(
                    searchResult =  searchResultState.searchResult.toMutableList().apply {
                        val index = indexOfFirst { item ->
                            item.data == kakaoSearchData.data
                        }

                        if(index != -1) {
                            this[index] = kakaoSearchData.copy(isFavorite = !kakaoSearchData.isFavorite)
                        }
                    }
                )
            }
        }
    }

    fun onClose() {
        _searchList.value = SearchResultState(
            query = "",
            page = 1,
            pageable = false,
            searchResult = emptyList()
        )

        _searchState.value = SearchUiState.IDLE
    }

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }

    fun onCloseButtonVisibleChanged(active: Boolean) {
        savedStateHandle[CLOSE_BUTTON_VISIBLE] = active
    }
}

enum class SearchUiState {
    IDLE,
    LOADING,
    EMPTY,
    RESULT,
    ERROR,
}
