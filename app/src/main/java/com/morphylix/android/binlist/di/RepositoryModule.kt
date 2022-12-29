package com.morphylix.android.binlist.di

import com.morphylix.android.binlist.data.RepositoryImpl
import com.morphylix.android.binlist.data.cache.CardDao
import com.morphylix.android.binlist.data.network.BinListApi
import com.morphylix.android.binlist.domain.Repository
import com.morphylix.android.binlist.domain.model.cache.CacheMapper
import com.morphylix.android.binlist.domain.model.network.NetworkMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        binListApi: BinListApi,
        networkMapper: NetworkMapper,
        cardDao: CardDao,
        cacheMapper: CacheMapper
    ): Repository {
        return RepositoryImpl(binListApi, networkMapper, cardDao, cacheMapper)
    }
}