package com.ahrijksmuseum.domain.usecases

import com.ahrijksmuseum.domain.Repository
import com.ahrijksmuseum.domain.models.ArtObjectDetails
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoadArtObjectDetailsUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var useCase: LoadArtObjectDetailsUseCase

    private val coroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        repository = mockk()

        useCase = LoadArtObjectDetailsUseCase(repository)
    }

    @Test
    fun `when use case is executed then repository loadArtObjectDetails should be called`() =
        coroutineDispatcher.runBlockingTest {
            val objectNumber = "objectNumber"
            val forceRefresh = false
            val artObjectDetails = buildArtObjectDetails()
            val flow = flow {
                emit(artObjectDetails)
            }

            coEvery {
                repository.loadArtObjectDetails(objectNumber, forceRefresh)
            } returns flow

            useCase.invoke(objectNumber)

            coVerify {
                repository.loadArtObjectDetails(objectNumber, forceRefresh)
            }
        }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun buildArtObjectDetails() = ArtObjectDetails(
        id = "id",
        objectNumber = "objectNumber",
        title = "title",
        longTitle = "longTitle",
        subTitle = "subTitle",
        scLabelLine = "scLabelLine",
        description = "description",
        principalOrFirstMaker = "principalOrFirstMaker",
        materials = "materials",
        techniques = "techniques",
        productionPlaces = "productionPlaces",
        dating = "dating",
        documentation = "documentation"
    )

}
