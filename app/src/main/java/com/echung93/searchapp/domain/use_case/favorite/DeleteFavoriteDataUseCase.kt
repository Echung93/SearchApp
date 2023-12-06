package com.echung93.searchapp.domain.use_case.favorite

import com.echung93.searchapp.data.model.FavoriteData
import com.echung93.searchapp.domain.repository.FavoriteRepository
import com.echung93.searchapp.model.KakaoSearchData
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DeleteFavoriteDataUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend operator fun invoke(kakaoSearchData: KakaoSearchData) {
        val list = favoriteRepository.getFavoriteData().first().toList()
        val index = list.indexOfFirst { it.item == kakaoSearchData.data }

        if (index != -1) {
            favoriteRepository.deleteFavorite(
                FavoriteData(
                    id = list[index].id,
                    item = kakaoSearchData.data
                )
            )
        }
    }
}