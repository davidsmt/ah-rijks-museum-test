package com.ahrijksmuseum.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.ahrijksmuseum.domain.usecases.LoadArtObjectsUseCase
import com.ahrijksmuseum.presentation.coroutines.CoroutineContextProvider
import com.ahrijksmuseum.presentation.mappers.RijksMuseumUiMapper
import com.ahrijksmuseum.presentation.models.ArtObjectUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


class ArtObjectsViewModel @Inject constructor(
    private val loadArtObjectsUseCase: LoadArtObjectsUseCase,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    private val state: MutableStateFlow<PagingData<ArtObjectUiModel>> =
        MutableStateFlow(PagingData.empty())
    val uiState: StateFlow<PagingData<ArtObjectUiModel>> = state

    fun start() {
        viewModelScope.launch(coroutineContextProvider.io()) {
            loadArtObjectsUseCase()
                .cachedIn(viewModelScope).map { pagingData ->
                    pagingData.map {
                        RijksMuseumUiMapper.map(it)
                    }.insertSeparators { before: ArtObjectUiModel.ArtObject?, after: ArtObjectUiModel.ArtObject? ->
                        if (after != null && (before == null || before.principalOrFirstMaker != after.principalOrFirstMaker)) {
                            ArtObjectUiModel.ArtistHeader(after.principalOrFirstMaker)
                        } else {
                            null
                        }
                    }
                }.collectLatest {
                    state.value = it
                }
        }
    }

}