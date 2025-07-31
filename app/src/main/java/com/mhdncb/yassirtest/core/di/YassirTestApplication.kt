package com.mhdncb.yassirtest.core.di

import android.app.Application
import com.mhdncb.yassirtest.core.di.module.baseModule
import com.mhdncb.yassirtest.core.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class YassirTestApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            printLogger(Level.DEBUG)
            androidContext(this@YassirTestApplication)
            modules(
                baseModule,
                viewModelModule
            )
        }
    }
}