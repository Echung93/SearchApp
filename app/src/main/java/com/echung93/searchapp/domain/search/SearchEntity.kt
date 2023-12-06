package com.echung93.searchapp.domain.search

import com.echung93.searchapp.model.SearchData

data class SearchEntity (
    val isEnd: Boolean,
    val itemList: List<SearchData>
)