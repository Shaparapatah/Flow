package com.shaparapatah.stopwatch.application

import android.app.Application
import com.shaparapatah.stopwatch.mainViewModel
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(mainViewModel)
        }
    }
}