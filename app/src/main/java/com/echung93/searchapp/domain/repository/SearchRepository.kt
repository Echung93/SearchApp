package com.echung93.searchapp.domain.repository

import com.echung93.searchapp.domain.search.SearchEntity
import com.echung93.searchapp.domain.util.Resource

interface SearchRepository {

    suspend fun getSearchData(query: String, page: Int? = 1, size: Int? = 50): Resource<SearchEntity>
}