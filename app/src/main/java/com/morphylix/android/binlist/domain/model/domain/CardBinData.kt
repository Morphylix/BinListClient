package com.morphylix.android.binlist.domain.model.domain

data class CardBinData(
    var bin: String,
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
