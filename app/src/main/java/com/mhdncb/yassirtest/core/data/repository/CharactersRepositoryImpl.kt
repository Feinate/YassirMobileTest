package com.mhdncb.yassirtest.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mhdncb.yassirtest.core.data.source.remote.RickAndMortySource
import com.mhdncb.yassirtest.core.data.source.remote.paging.CharactersPagingManager
import com.mhdncb.yassirtest.core.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow

class CharactersRepositoryImpl(
    private val rickAndMortySource: RickAndMortySource
) : CharactersRepository {

    override fun getCharactersPaging(): Flow<PagingData<com.mhdncb.yassirtest.core.domain.model.Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharactersPagingManager(rickAndMortySource)
            }
        ).flow
    }
}