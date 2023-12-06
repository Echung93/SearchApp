package com.echung93.searchapp.data

import androidx.room.*
import com.echung93.searchapp.data.model.FavoriteData
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite_table")
    fun getAllData(): Flow<List<FavoriteData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addData(favoriteData: FavoriteData)

    @Delete
    suspend fun deleteData(favoriteData: FavoriteData)
}