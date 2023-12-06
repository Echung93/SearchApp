package com.echung93.searchapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echung93.searchapp.domain.repository.SearchRepository
import com.echung93.searchapp.domain.search.SearchState
import com.echung93.searchapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
): ViewModel() {

    var state by mutableStateOf(SearchState())
        private set
    fun loadSearchData() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )

            when (val result = repository.getSearchData("강아지")) {
                is Resource.Success -> {
                    state = state.copy(
                        searchInfo = result.data,
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        searchInfo = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }

}