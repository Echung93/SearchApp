package com.echung93.searchapp.domain.use_case.favorite

import com.echung93.searchapp.domain.repository.FavoriteRepository
import com.echung93.searchapp.model.KakaoSearchData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteDataUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    operator fun invoke(): Flow<List<KakaoSearchData>> {
        return favoriteRepository.getFavoriteData().map { list ->
            list.map {
                KakaoSearchData(
                    isFavorite = true,
                    data = it.item
                )
            }
        }
    }
}