package com.echung93.searchapp.domain.repository

import com.echung93.searchapp.domain.search.SearchEntity
import com.echung93.searchapp.domain.util.Resource

interface SearchRepository {

    suspend fun getSearchData(query: String, page: Int, size: Int): Resource<SearchEntity>
}