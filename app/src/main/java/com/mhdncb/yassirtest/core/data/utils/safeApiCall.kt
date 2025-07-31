package com.mhdncb.yassirtest.core.data.utils

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import timber.log.Timber

suspend fun <D> safeApiCall(
    apiCall: suspend () -> HttpResponse,
    apiResult: suspend (HttpResponse) -> ApiResult<D, NetworkError>
): ApiResult<D, NetworkError> {
    val response = try {
        apiCall()
    } catch (e: UnresolvedAddressException) {
        Timber.e(e.message.toString())
        return ApiResult.Error(NetworkError.Unknown(e.message ?: "UnresolvedAddressException"))
    } catch (e: SerializationException) {
        Timber.e(e.message.toString())
        return ApiResult.Error(NetworkError.Serialization(e.message ?: "SerializationException"))
    } catch (e: Exception) {
        Timber.e(e.message.toString())
        return ApiResult.Error(NetworkError.Unknown(e.message ?: "Exception"))
    }

    return when(response.status.value) {
        in 200..299 -> {
            apiResult(response)
        }
        401 -> ApiResult.Error(NetworkError.Unauthorized(response.status.description))
        408 -> ApiResult.Error(NetworkError.RequestTimeout(response.status.description))
        409 -> ApiResult.Error(NetworkError.Conflict(response.status.description))
        413 -> ApiResult.Error(NetworkError.PayloadTooLarge(response.status.description))
        429 -> ApiResult.Error(NetworkError.TooManyRequests(response.status.description))
        in 500..599 -> ApiResult.Error(NetworkError.ServerError(response.status.description))
        else -> ApiResult.Error(NetworkError.Unknown(response.status.description))
    }
}