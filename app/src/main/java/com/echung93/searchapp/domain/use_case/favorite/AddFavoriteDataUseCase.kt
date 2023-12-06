package com.echung93.searchapp.domain.use_case.favorite

import com.echung93.searchapp.data.model.FavoriteData
import com.echung93.searchapp.domain.repository.FavoriteRepository
import com.echung93.searchapp.model.KakaoSearchData
import javax.inject.Inject

class AddFavoriteDataUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(kakaoSearchData: KakaoSearchData) {
        favoriteRepository.addFavorite(
            FavoriteData(
                id = 0,
                item = kakaoSearchData.data
            )
        )
    }
}