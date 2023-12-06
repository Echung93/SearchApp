package com.echung93.andoridtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echung93.andoridtest.domain.use_case.search.GetSearchDataUseCase
import com.echung93.searchapp.domain.util.Resource
import com.echung93.searchapp.model.KakaoSearchData
import com.echung93.searchapp.presentation.search.SearchResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchDataUseCase: GetSearchDataUseCase
) : ViewModel() {

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

        }
    }

    fun onClose() {
        _searchList.value = SearchResultState(
            query = "",
            page = 1,
            pageable = false
        )

        _searchState.value = SearchUiState.IDLE
    }
}

enum class SearchUiState {
    IDLE,
    LOADING,
    EMPTY,
    RESULT,
    ERROR,
}
