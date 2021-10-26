package com.sample.dishes.data.remote.api

sealed class ResponseResult<T> {

    data class Success<T>(val data: T) : ResponseResult<T>()
    data class Error<T>(val message: String) : ResponseResult<T>()
    data class NetworkException<T>(val networkError: String) : ResponseResult<T>()
    data class ErrorException<T>(val exception: String) : ResponseResult<T>()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun <T> error(message: String) = Error<T>(message)
        fun <T> networkError(networkError: String) = NetworkException<T>(networkError)
        fun <T> exception(error: String) = ErrorException<T>(error)
    }
}


sealed class ViewState<T> {
    class Loading<T> : ViewState<T>()
    class Success<T>(val data: T) : ViewState<T>()
    class Failed<T>(val message: String) : ViewState<T>()
    class NetworkFailed<T>(val message: String) : ViewState<T>()
    class ExceptionError<T>(val message: String) : ViewState<T>()
    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> failed(message: String) = Failed<T>(message)
        fun <T> networkFailed(networkMessage: String) = NetworkFailed<T>(networkMessage)
        fun <T> exceptionError(exception: String) = ExceptionError<T>(exception)
    }
}
