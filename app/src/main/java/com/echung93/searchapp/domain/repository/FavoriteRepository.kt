package com.echung93.searchapp.domain.repository

import com.echung93.searchapp.data.model.FavoriteData
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getFavoriteData(): Flow<List<FavoriteData>>

    suspend fun addFavorite(favoriteData: FavoriteData)

    suspend fun deleteFavorite(favoriteData: FavoriteData)
}