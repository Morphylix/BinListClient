package com.morphylix.android.binlist.domain.model.cache

import com.morphylix.android.binlist.domain.model.domain.CardBinData
import com.morphylix.android.binlist.util.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor(): EntityMapper<CardBinDataCacheEntity, CardBinData> {

    override fun mapFromEntity(entity: CardBinDataCacheEntity): CardBinData {
        return CardBinData(
            bin = entity.bin,
            scheme = entity.scheme,
            brand = entity.brand,
            length = entity.length,
            luhn = entity.luhn,
            type = entity.type,
            isPrepaid = entity.isPrepaid,
            country = entity.country,
            countryEmoji = entity.countryEmoji,
            countryLatitude = entity.countryLatitude,
            countryLongitude = entity.countryLongitude,
            bank = entity.bank,
            bankCity = entity.bankCity,
            bankUrl = entity.bankUrl,
            bankPhone = entity.bankPhone
        )
    }

    override fun mapToEntity(model: CardBinData): CardBinDataCacheEntity {
        return CardBinDataCacheEntity(
            bin = model.bin,
            scheme = model.scheme,
            brand = model.brand,
            length = model.length,
            luhn = model.luhn,
            type = model.type,
            isPrepaid = model.isPrepaid,
            country = model.country,
            countryEmoji = model.countryEmoji,
            countryLatitude = model.countryLatitude,
            countryLongitude = model.countryLongitude,
            bank = model.bank,
            bankCity = model.bankCity,
            bankUrl = model.bankUrl,
            bankPhone = model.bankPhone
        )
    }

    fun mapFromEntityList(entityList: List<CardBinDataCacheEntity>): List<CardBinData> {
        return entityList.map { mapFromEntity(it) }
    }
}