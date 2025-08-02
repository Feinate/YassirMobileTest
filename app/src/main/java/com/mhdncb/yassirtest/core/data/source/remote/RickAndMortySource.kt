package com.mhdncb.yassirtest.core.data.source.remote

import com.mhdncb.yassirtest.core.data.model.CharacterDto
import com.mhdncb.yassirtest.core.data.model.CharactersResponse
import com.mhdncb.yassirtest.core.data.utils.ApiResult
import com.mhdncb.yassirtest.core.data.utils.Consts
import com.mhdncb.yassirtest.core.data.utils.NetworkError
import com.mhdncb.yassirtest.core.data.utils.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RickAndMortySource(
    private val httpClient: HttpClient
) {
    suspend fun fetchCharacters(
        pageIndex: Int
    ): ApiResult<List<CharacterDto>, NetworkError> {
        return safeApiCall(
            apiCall = {
                httpClient.get(
                    urlString = "${Consts.RICK_AND_MORT_BASE_URL}/character/?page=${pageIndex}"
                ) {
                    contentType(ContentType.Application.Json)
                }
            },
            apiResult = { response ->
                val data = response.body<CharactersResponse?>()

                if (data != null) {
                    ApiResult.Success(data.results)
                } else {
                    ApiResult.Error(NetworkError.Unknown(response.status.description))
                }
            }
        )
    }
}