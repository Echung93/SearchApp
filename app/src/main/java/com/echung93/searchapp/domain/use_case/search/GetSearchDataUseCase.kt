package com.echung93.searchapp.domain.use_case.search

import com.echung93.searchapp.domain.repository.FavoriteRepository
import com.echung93.searchapp.domain.repository.SearchRepository
import com.echung93.searchapp.domain.util.Resource
import com.echung93.searchapp.model.KakaoSearchData
import com.echung93.searchapp.model.SearchData
import com.echung93.searchapp.presentation.search.SearchResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchDataUseCase @Inject constructor(
    private val repository: SearchRepository,
    private val favoriteRepository: FavoriteRepository
) {
    private val kakaoSearchList = mutableListOf<SearchData>()
    private var query = ""
    private var pageable: Boolean = true

    suspend operator fun invoke(
        newQuery: String,
        page: Int,
        refresh: Boolean = false,
        searchResultState: SearchResultState
    ): Flow<Resource<SearchResultState>> = flow {
        if (!refresh && query != newQuery) {
            query = newQuery
            kakaoSearchList.clear()
            pageable = true
        }

        while (kakaoSearchList.size < page * 50 && query != "" && pageable) {
            when (val result = repository.getSearchData(newQuery, page, 50)) {
                is Resource.Success -> {
                    if (result.data?.itemList?.size == 0) {
                        emit(
                            Resource.Success(
                                SearchResultState(
                                    query = query,
                                    page = searchResultState.page,
                                    pageable = searchResultState.pageable,
                                    searchResult = emptyList()
                                )
                            )
                        )
                    } else {
                        kakaoSearchList.addAll(result.data!!.itemList)
                        pageable = !result.data.isEnd
                    }
                }
                else -> {
                }
            }
            break
        }


        if (newQuery != "") {
            val favoriteList = favoriteRepository.getFavoriteData().first().toList()
            val favoriteData = favoriteList.map { it.item }

            val itemList: List<KakaoSearchData> =
                kakaoSearchList.subList(
                    0,
                    (page * 50).coerceAtMost(
                        kakaoSearchList.size
                    )
                )
                    .map { kakaoData ->
                        KakaoSearchData(
                            favoriteData.contains(kakaoData),
                            kakaoData
                        )
                    }

            emit(
                Resource.Success(
                    SearchResultState(
                        query = query,
                        page = page,
                        pageable = pageable,
                        searchResult = itemList
                    )
                )
            )
        } else {
            emit(Resource.Idle)
        }
    }
}