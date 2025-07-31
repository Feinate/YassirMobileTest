package com.mhdncb.yassirtest.core.di.module

import com.mhdncb.yassirtest.core.data.utils.createHttpClient
import org.koin.dsl.module

val baseModule = module {
    single { createHttpClient() }
}