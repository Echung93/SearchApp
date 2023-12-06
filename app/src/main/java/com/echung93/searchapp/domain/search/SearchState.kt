package com.echung93.searchapp.domain.search

data class SearchState(
    val searchInfo: SearchEntity? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)