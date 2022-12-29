package com.morphylix.android.binlist.domain.model.network

data class CardCountryNetworkEntity(
    val numeric: String,
    val alpha2: String,
    val name: String,
    val emoji: String,
    val currency: String,
    val latitude: Int,
    val longitude: Int
)
