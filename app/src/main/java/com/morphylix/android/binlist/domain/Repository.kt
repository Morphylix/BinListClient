package com.morphylix.android.binlist.domain

import com.morphylix.android.binlist.domain.model.domain.CardBinData

interface Repository {
    suspend fun fetchBinData(bin: String): CardBinData

    suspend fun cacheBinData(cardBinData: CardBinData)

    suspend fun getAllCachedBinData(): List<CardBinData>

    suspend fun clearAllCachedData()

    suspend fun loadCachedBinData(bin: String): CardBinData?
}