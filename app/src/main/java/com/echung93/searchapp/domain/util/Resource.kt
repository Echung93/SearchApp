package com.echung93.searchapp.domain.util

sealed class Resource<out T>(val data: T? = null, val message: String? = null) {
    object Idle: Resource<Nothing>()
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data:T? = null): Resource<T>(data, message)
}