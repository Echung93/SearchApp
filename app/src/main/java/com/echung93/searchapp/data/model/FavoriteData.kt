package com.echung93.searchapp.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.echung93.searchapp.model.KakaoSearchData
import com.echung93.searchapp.util.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class FavoriteData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @Embedded
    val item: KakaoSearchData
)
