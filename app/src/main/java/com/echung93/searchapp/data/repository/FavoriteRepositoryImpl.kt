package com.echung93.searchapp.data.repository

import com.echung93.searchapp.data.FavoriteDao
import com.echung93.searchapp.data.model.FavoriteData
import com.echung93.searchapp.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
): FavoriteRepository {
    override fun getFavoriteData(): Flow<List<FavoriteData>> =
        favoriteDao.getAllData()

    override suspend fun addFavorite(favoriteData: FavoriteData) {
        favoriteDao.addData(favoriteData)
    }

    override suspend fun deleteFavorite(favoriteData: FavoriteData) {
        favoriteDao.deleteData(favoriteData)
    }
}