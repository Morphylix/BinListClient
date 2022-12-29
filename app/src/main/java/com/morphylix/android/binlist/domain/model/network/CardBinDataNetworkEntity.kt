package com.morphylix.android.binlist.domain.model.network

data class CardBinDataNetworkEntity(
    val number: CardNumberNetworkEntity,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val country: CardCountryNetworkEntity,
    val bank: CardBankNetworkEntity
    )