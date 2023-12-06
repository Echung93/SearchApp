package com.echung93.searchapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.echung93.searchapp.data.model.FavoriteData
import com.echung93.searchapp.util.Converters

@Database(entities = [FavoriteData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FavoriteDatabase: RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
}