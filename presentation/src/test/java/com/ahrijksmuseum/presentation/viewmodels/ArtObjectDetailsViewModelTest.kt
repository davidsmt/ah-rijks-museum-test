package com.ahrijksmuseum.presentation.viewmodels

import androidx.arch.core.executor.ArchTaskExecutor
import com.ahrijksmuseum.domain.models.ArtObjectDetails
import com.ahrijksmuseum.domain.usecases.LoadArtObjectDetailsUseCase
import com.ahrijksmuseum.presentation.mappers.ArtObjectDetailsUiMapper
import com.ahrijksmuseum.presentation.models.ArtObjectDetailsUiModel
import com.ahrijksmuseum.presentation.models.UiState
import com.ahrijksmuseum.presentation.utils.TestCoroutineContextProvider
import com.ahrijksmuseum.presentation.utils.TestTaskExecutor
import com.ahrijksmuseum.presentation.utils.TestableObserver
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ArtObjectDetailsViewModelTest {

    private lateinit var loadArtObjectDetailsUseCase: LoadArtObjectDetailsUseCase
    private lateinit var viewModel: ArtObjectDetailsViewModel

    private val coroutineDispatcher = TestCoroutineDispatcher()
    private val objectNumber = "objectNumber"

    @Before
    fun setUp() {
        ArchTaskExecutor.getInstance().setDelegate(TestTaskExecutor())
        Dispatchers.setMain(Dispatchers.Unconfined)

        loadArtObjectDetailsUseCase = mockk()

        viewModel = ArtObjectDetailsViewModel(
            loadArtObjectDetailsUseCase,
            TestCoroutineContextProvider()
        )
    }

    @Test
    fun `when start and data has not been loaded yet then loading should be shown`() =
        coroutineDispatcher.runBlockingTest {
            //Arrange
            val stateObserver = TestableObserver<UiState<ArtObjectDetailsUiModel>>()

            val artObjectDetails = buildArtObjectDetails()
            val flow = flow {
                emit(artObjectDetails)
            }

            // When
            coEvery {
                loadArtObjectDetailsUseCase(objectNumber)
            } returns flow

            viewModel.uiState.observeForever(stateObserver)
            viewModel.start(objectNumber)

            //Assert
            assertEquals(stateObserver.history[0], UiState.Loading<ArtObjectDetailsUiModel>())
        }

    @Test
    fun `when start and data has been loaded then content should be shown`() =
        coroutineDispatcher.runBlockingTest {
            //Arrange
            val stateObserver = TestableObserver<UiState<ArtObjectDetailsUiModel>>()

            val artObjectDetails = buildArtObjectDetails()
            val artObjectDetailsUiModel = ArtObjectDetailsUiMapper.map(artObjectDetails)
            val flow = flow {
                emit(artObjectDetails)
            }

            // When
            coEvery {
                loadArtObjectDetailsUseCase(objectNumber)
            } returns flow

            viewModel.uiState.observeForever(stateObserver)
            viewModel.start(objectNumber)

            //Assert
            stateObserver.assertAllEmitted(listOf(UiState.Loading(), UiState.Loaded(artObjectDetailsUiModel)))
        }

    @Test
    fun `when start and data has not been loaded yet then error should be shown`() =
        coroutineDispatcher.runBlockingTest {
            //Arrange
            val stateObserver = TestableObserver<UiState<ArtObjectDetailsUiModel>>()

            val exception = Exception()
            val flow = flow<ArtObjectDetails> {
                throw exception
            }

            // When
            coEvery {
                loadArtObjectDetailsUseCase(objectNumber)
            } returns flow

            viewModel.uiState.observeForever(stateObserver)
            viewModel.start(objectNumber)

            //Assert
            stateObserver.assertAllEmitted(listOf(UiState.Loading(), UiState.Error(exception)))
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
