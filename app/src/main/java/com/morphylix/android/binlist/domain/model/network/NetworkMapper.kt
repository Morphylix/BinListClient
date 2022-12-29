package com.morphylix.android.binlist.domain.model.network

import com.morphylix.android.binlist.domain.model.domain.CardBinData
import com.morphylix.android.binlist.util.EntityMapper
import com.morphylix.android.binlist.util.convertToYesOrNo
import javax.inject.Inject

class NetworkMapper @Inject constructor(): EntityMapper<CardBinDataNetworkEntity, CardBinData> {

    override fun mapFromEntity(entity: CardBinDataNetworkEntity): CardBinData {
        return CardBinData(
            bin = "",
            scheme = entity.scheme,
            brand = entity.brand,
            length = entity.number.length.toString(),
            luhn = entity.number.luhn.convertToYesOrNo(),
            type = entity.type,
            isPrepaid = entity.prepaid.convertToYesOrNo(),
            country = entity.country.name,
            countryEmoji = entity.country.emoji,
            countryLatitude = entity.country.latitude,
            countryLongitude = entity.country.longitude,
            bank = entity.bank.name,
            bankCity = entity.bank.city,
            bankUrl = entity.bank.url,
            bankPhone = entity.bank.phone
        )
    }

    override fun mapToEntity(model: CardBinData): CardBinDataNetworkEntity {
        TODO("No need to implement it")
    }

}