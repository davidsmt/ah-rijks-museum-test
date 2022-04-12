package com.ahrijksmuseum.domain.usecases

import com.ahrijksmuseum.domain.Repository
import javax.inject.Inject

class LoadArtObjectsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.loadArtObjects()
}