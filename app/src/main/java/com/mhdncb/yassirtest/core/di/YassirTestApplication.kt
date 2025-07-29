package com.mhdncb.yassirtest.core.di

import android.app.Application
import com.mhdncb.yassirtest.core.di.module.baseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class YassirTestApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger(Level.DEBUG)
            androidContext(this@YassirTestApplication)
            modules(
                baseModule
            )
        }
    }
}