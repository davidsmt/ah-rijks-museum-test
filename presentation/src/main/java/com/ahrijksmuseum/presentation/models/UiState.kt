package com.ahrijksmuseum.presentation.models

sealed class UiState<T> {

    data class Loading<T>(val data: T? = null) : UiState<T>()

    data class Error<T>(val error: Throwable? = null) : UiState<T>()

    data class Loaded<T>(val data: T) : UiState<T>()

}