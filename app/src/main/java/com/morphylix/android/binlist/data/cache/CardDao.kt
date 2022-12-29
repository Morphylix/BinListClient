package com.morphylix.android.binlist.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.morphylix.android.binlist.domain.model.cache.CardBinDataCacheEntity
import com.morphylix.android.binlist.domain.model.domain.CardBinData

@Dao
interface CardDao {
    @Query("SELECT * FROM CardBinDataCacheEntity")
    suspend fun getAllCards(): List<CardBinDataCacheEntity>

    @Query("SELECT * FROM CardBinDataCacheEntity WHERE (bin = :bin)")
    suspend fun getCard(bin: String): CardBinDataCacheEntity?

    @Insert(onConflict = REPLACE)
    suspend fun addCard(cardBinDataCacheEntity: CardBinDataCacheEntity)

    @Query("DELETE FROM CardBinDataCacheEntity")
    suspend fun clearAll()
}