package com.ahrijksmuseum.presentation.models

sealed class UiState<T> {

    class Loading<T> : UiState<T>()

    class Error<T>(val error: Throwable? = null) : UiState<T>()

    class Loaded<T>(val data: T) : UiState<T>()

}