package com.mhdncb.yassirtest.core.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharactersPaging(): Flow<PagingData<com.mhdncb.yassirtest.core.domain.model.Character>>
}