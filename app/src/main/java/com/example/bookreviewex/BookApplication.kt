package com.example.bookreviewex

import android.app.Application
import com.example.bookreviewex.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BookApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BookApplication)
            modules(appModules)
        }
    }
}