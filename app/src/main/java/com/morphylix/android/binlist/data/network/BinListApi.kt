package com.morphylix.android.binlist.data.network

import com.morphylix.android.binlist.domain.model.network.CardBinDataNetworkEntity
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface BinListApi {
    @GET("{bin}")
    suspend fun fetchBinData(@Path("bin") bin: String): CardBinDataNetworkEntity
}