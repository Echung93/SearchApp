package com.echung93.searchapp.data.repository

import com.echung93.searchapp.R
import com.echung93.searchapp.data.mapper.SearchDataMapper.toDomain
import com.echung93.searchapp.data.remote.SearchApi
import com.echung93.searchapp.domain.repository.SearchRepository
import com.echung93.searchapp.domain.search.SearchEntity
import com.echung93.searchapp.domain.util.Resource
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val api: SearchApi
): SearchRepository {
    override suspend fun getSearchData(
        query: String,
        page: Int,
        size: Int
    ): Resource<SearchEntity> {
        return try{
            Resource.Success(
                data = api.getSearchDataResponse(
                    query = query,
                    page = page,
                    sort = "recency",
                    size = size
                ).toDomain()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: (R.string.error_message).toString())
        }
    }
}