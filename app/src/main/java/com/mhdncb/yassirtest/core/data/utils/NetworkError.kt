package com.mhdncb.yassirtest.core.data.utils

sealed class NetworkError(val message: String) : Error {
    class RequestTimeout(message: String) : NetworkError(message)
    class Unauthorized(message: String) : NetworkError(message)
    class Conflict(message: String) : NetworkError(message)
    class TooManyRequests(message: String) : NetworkError(message)
    class NoInternet(message: String) : NetworkError(message)
    class PayloadTooLarge(message: String) : NetworkError(message)
    class ServerError(message: String) : NetworkError(message)
    class Serialization(message: String) : NetworkError(message)
    class Unknown(message: String = "Une erreur inconnue est survenue") : NetworkError(message)
}