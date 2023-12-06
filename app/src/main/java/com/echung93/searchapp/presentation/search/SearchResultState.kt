package com.echung93.searchapp.presentation.search

import com.echung93.searchapp.model.KakaoSearchData

data class SearchResultState(
    val query: String,
    val page: Int,
    val pageable: Boolean,
    val searchResult: List<KakaoSearchData> = emptyList()
)
