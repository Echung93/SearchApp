package com.echung93.searchapp.domain.search

data class SearchEntity (
    val isEnd: Boolean,
    val itemList: List<SearchData>
)