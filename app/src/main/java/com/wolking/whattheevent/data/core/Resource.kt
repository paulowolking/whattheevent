package com.wolking.whattheevent.data.core

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()

    data class Success<out T>(val data: T) : Resource<T>()

    sealed class Error : Resource<Nothing>() {

        /**
         * Wrapper class for handling exceptions, such as IO, NPE or custom,
         * except SocketTimeoutException (@see [Result.Error.NoInternetConnection])
         */
        data class GenericError(val exception: Throwable) : Error()

        /**
         * Wrapper class for 4XX HTTP errors without any additional data (only inner code and message)
         */
        data class RequestError(val errorMessage: String) : Error()

        /**
         * Wrapper class for case when internet connection is missing or bad
         * and request raises [java.net.SocketTimeoutException]
         */
        object NoInternetConnection : Error()

        /**
         * Wrapper class for 5XX HTTP errors.
         */
        data class ServerError(val code: Int) : Error()
    }
}