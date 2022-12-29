package com.morphylix.android.binlist.di

import android.content.Context
import com.morphylix.android.binlist.presentation.bin_details.BinDetailsFragment
import com.morphylix.android.binlist.presentation.bin_input.BinInputFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, CacheModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: BinInputFragment)
    fun inject(fragment: BinDetailsFragment)
}