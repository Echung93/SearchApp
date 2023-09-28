package com.echung93.searchapp.domain.search

import java.util.*

data class SearchData(
    val title: String,
    val doc_url: String,
    val image_url: String,
    val thumbnail_url: String,
    val dateTime: Date
)