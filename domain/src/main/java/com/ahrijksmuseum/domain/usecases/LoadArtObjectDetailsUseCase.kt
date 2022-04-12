package com.ahrijksmuseum.domain.usecases

import com.ahrijksmuseum.domain.Repository
import javax.inject.Inject

class LoadArtObjectDetailsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(objectNumber: String, forceRefresh: Boolean = false) = repository.loadArtObjectDetails(objectNumber, forceRefresh)
}