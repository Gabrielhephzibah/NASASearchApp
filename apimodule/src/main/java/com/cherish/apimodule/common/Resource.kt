package com.cherish.apimodule.common

sealed class Resource<out T>(
    val data: T? = null,
    val error: Throwable? = null,
    val loading: Boolean? = true
) {
    data class Loading(private val state: Boolean? = true) : Resource<Nothing>(loading = state)
    data class Error(private val mError: Throwable? = null) : Resource<Nothing>(error = mError)
    data class Success<T>(private val mData: T) : Resource<T>(data = mData)
}