package com.morphylix.android.binlist.domain.model.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardBinDataCacheEntity(
    @PrimaryKey val bin: String,
    val scheme: String?,
    val brand: String?,
    val length: String?,
    val luhn: String?,
    val type: String?,
    val isPrepaid: String?,
    val country: String?,
    val countryEmoji: String?,
    val countryLatitude: Int?,
    val countryLongitude: Int?,
    val bank: String?,
    val bankCity: String?,
    val bankUrl: String?,
    val bankPhone: String?
    )
