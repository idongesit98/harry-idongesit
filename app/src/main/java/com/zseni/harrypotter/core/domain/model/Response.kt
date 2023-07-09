package com.zseni.harrypotter.core.domain.model

sealed class Response<out T>{
    data class Success<T>(val data:T): Response<T>()
    data class Error<T>(val error:T): Response<T>()
}