package com.ahrijksmuseum.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ahrijksmuseum.domain.models.ArtObjectDetails
import com.ahrijksmuseum.domain.usecases.LoadArtObjectDetailsUseCase
import com.ahrijksmuseum.presentation.coroutines.CoroutineContextProvider
import com.ahrijksmuseum.presentation.mappers.ArtObjectDetailsUiMapper
import com.ahrijksmuseum.presentation.models.ArtObjectDetailsUiModel
import com.ahrijksmuseum.presentation.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArtObjectDetailsViewModel @Inject constructor(
    private val loadArtObjectDetailsUseCase: LoadArtObjectDetailsUseCase,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    private lateinit var _objectNumber: String

    private val state: MutableStateFlow<UiState<ArtObjectDetailsUiModel>> =
        MutableStateFlow(UiState.Loading())
    val uiState = state.asLiveData()

    fun start(objectNumber: String) {
        _objectNumber = objectNumber
        viewModelScope.launch(coroutineContextProvider.io()) {
            loadArtObjectDetailsUseCase(_objectNumber).onStart {
                state.emit(UiState.Loading())
            }.catch { e ->
                state.emit(UiState.Error(e))
            }.collect {
                updateUiState(it)
            }
        }
    }

    private suspend fun updateUiState(artObjectDetails: ArtObjectDetails) {
        state.emit(UiState.Loaded(ArtObjectDetailsUiMapper.map(artObjectDetails)))
    }

    fun onRefresh() {
        viewModelScope.launch(coroutineContextProvider.io()) {
            loadArtObjectDetailsUseCase(_objectNumber, true).onStart {
                state.emit(UiState.Loading())
            }.catch { e ->
                state.emit(UiState.Error(e))
            }.collect {
                updateUiState(it)
            }
        }
    }
}