package com.mvi_example

import android.app.Application
import com.mvi_example.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(
                appModule
            )
        }

        // Initialize the ToastUtil with the application context
        AndroidToastUtil.initialize(this)
    }
}