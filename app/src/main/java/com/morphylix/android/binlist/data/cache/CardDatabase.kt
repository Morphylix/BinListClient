package com.morphylix.android.binlist.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.morphylix.android.binlist.domain.model.cache.CardBinDataCacheEntity


@Database(entities=[CardBinDataCacheEntity::class], version = 1)
abstract class CardDatabase: RoomDatabase() {
    abstract fun cardDao(): CardDao
}