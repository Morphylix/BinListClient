package com.morphylix.android.binlist.di

import android.content.Context
import androidx.room.Room
import com.morphylix.android.binlist.data.cache.CardDao
import com.morphylix.android.binlist.data.cache.CardDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


private const val DATABASE_NAME = "card-database"

@Module
class CacheModule {

    @Singleton
    @Provides
    fun provideCardDatabase(context: Context): CardDatabase {
        return Room.databaseBuilder(context, CardDatabase::class.java, DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideCardDao(cardDatabase: CardDatabase): CardDao {
        return cardDatabase.cardDao()
    }

}