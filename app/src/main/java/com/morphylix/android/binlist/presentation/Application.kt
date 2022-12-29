package com.morphylix.android.binlist.presentation

import android.app.Application
import com.morphylix.android.binlist.di.AppComponent
import com.morphylix.android.binlist.di.DaggerAppComponent

class Application: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}