package com.ahrijksmuseum.domain.usecases

import androidx.paging.PagingData
import com.ahrijksmuseum.domain.Repository
import com.ahrijksmuseum.domain.models.ArtObject
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoadArtObjectsUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var useCase: LoadArtObjectsUseCase

    private val coroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        repository = mockk()

        useCase = LoadArtObjectsUseCase(repository)
    }

    @Test
    fun `when use case is executed then repository loadArtObjects should be called`() =
        coroutineDispatcher.runBlockingTest {
            val flow = flowOf<PagingData<ArtObject>>()

            coEvery {
                repository.loadArtObjects()
            } returns flow

            useCase.invoke()

            coVerify {
                repository.loadArtObjects()
            }
        }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
