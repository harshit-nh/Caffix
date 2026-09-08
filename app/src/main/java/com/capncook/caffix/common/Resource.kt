package com.capncook.caffix.common

sealed class Resource<T>(val data: T? = null, val message: String? = null, val errorCode: String? = null) {

    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String, errorCode: String? = null): Resource<T>(data = null, message, errorCode)

    class Loading<T>(data: T? = null): Resource<T>(data)
}