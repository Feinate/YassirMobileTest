package com.mhdncb.yassirtest.core.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mhdncb.yassirtest.core.data.mapper.toDomain
import com.mhdncb.yassirtest.core.data.source.remote.RickAndMortySource
import com.mhdncb.yassirtest.core.data.utils.ApiResult

class CharactersPagingManager(
    private val rickAndMortySource: RickAndMortySource,
    private val query: String
) : PagingSource<Int, com.mhdncb.yassirtest.core.domain.model.Character>() {

    private val numOfOffScreenPage: Int = 4

    override fun getRefreshKey(state: PagingState<Int, com.mhdncb.yassirtest.core.domain.model.Character>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition)

        return anchorPage?.prevKey?.plus(numOfOffScreenPage) ?: anchorPage?.nextKey?.minus(numOfOffScreenPage)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.mhdncb.yassirtest.core.domain.model.Character> {
        val pageIndex = params.key ?: 1
        return try {
            when (val result = rickAndMortySource.fetchCharacters(query, pageIndex)) {
                is ApiResult.Success -> {
                    val characters = result.data // c'est List<CharacterDto>

                    return LoadResult.Page(
                        data = characters.map { it.toDomain() },
                        prevKey = if (pageIndex == 1) null else pageIndex - 1,
                        nextKey = if (characters.isEmpty()) null else pageIndex + 1
                    )
                }
                is ApiResult.Error -> {
                    LoadResult.Error(Exception(result.error.message))
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}