package com.morphylix.android.binlist.domain.model.network

data class CardNumberNetworkEntity(
    val length: Int,
    val luhn: Boolean
)
