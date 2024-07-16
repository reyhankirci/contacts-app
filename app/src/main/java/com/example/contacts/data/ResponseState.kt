package com.example.contacts.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart


sealed interface ResponseState<out T> {
    data class Success<T>(val data: T): ResponseState<T>
    data class Error(val exception: Throwable): ResponseState<Nothing>
    data object Loading: ResponseState<Nothing>
}

fun <T> Flow<T>.asResponseState(): Flow<ResponseState<T>> {
    return this
        .map<T, ResponseState<T>> {
            ResponseState.Success(it)
        }
        .onStart {
            emit(ResponseState.Loading)
        }
        .catch {
            emit(ResponseState.Error(it))
        }
}