package com.morphylix.android.binlist.data

import android.util.Log
import com.morphylix.android.binlist.data.cache.CardDao
import com.morphylix.android.binlist.data.network.BinListApi
import com.morphylix.android.binlist.domain.Repository
import com.morphylix.android.binlist.domain.model.cache.CacheMapper
import com.morphylix.android.binlist.domain.model.domain.CardBinData
import com.morphylix.android.binlist.domain.model.network.CardBinDataNetworkEntity
import com.morphylix.android.binlist.domain.model.network.NetworkMapper
import javax.inject.Inject

private const val TAG = "RepositoryImpl"

class RepositoryImpl
@Inject constructor(
    private val binListApi: BinListApi,
    private val networkMapper: NetworkMapper,
    private val cardDao: CardDao,
    private val cacheMapper: CacheMapper
) : Repository {

    override suspend fun fetchBinData(bin: String): CardBinData {
        Log.i(TAG, "FETCHING DATA FROM NETWORK")
        val cardBinDataNetworkEntity = binListApi.fetchBinData(bin)
        return networkMapper.mapFromEntity(cardBinDataNetworkEntity)
    }

    override suspend fun cacheBinData(cardBinData: CardBinData) {
        val cardBinDataCacheEntity = cacheMapper.mapToEntity(cardBinData)
        Log.i(TAG, "Caching ${cardBinData.bin}")
        cardDao.addCard(cardBinDataCacheEntity)
    }

    override suspend fun getAllCachedBinData(): List<CardBinData> {
        val cardBinDataCacheEntityList = cardDao.getAllCards()
        Log.i(TAG, "GOT ${cardBinDataCacheEntityList.size} elements")
        return cacheMapper.mapFromEntityList(cardBinDataCacheEntityList)
    }

    override suspend fun clearAllCachedData() {
        cardDao.clearAll()
    }

    override suspend fun loadCachedBinData(bin: String): CardBinData? {
        Log.i(TAG, "LOADING CACHED DATA")
        val cardBinDataCacheEntity = cardDao.getCard(bin)
        return if (cardBinDataCacheEntity != null) {
            cacheMapper.mapFromEntity(cardBinDataCacheEntity)
        } else null
    }
}