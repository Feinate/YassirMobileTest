package com.mhdncb.yassirtest.core.di.module

import com.mhdncb.yassirtest.core.data.repository.CharactersRepositoryImpl
import com.mhdncb.yassirtest.core.data.source.remote.RickAndMortySource
import com.mhdncb.yassirtest.core.data.utils.createHttpClient
import com.mhdncb.yassirtest.core.domain.repository.CharactersRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val baseModule = module {
    single { createHttpClient() }

    singleOf(::RickAndMortySource)

    singleOf(::CharactersRepositoryImpl).bind<CharactersRepository>()
}