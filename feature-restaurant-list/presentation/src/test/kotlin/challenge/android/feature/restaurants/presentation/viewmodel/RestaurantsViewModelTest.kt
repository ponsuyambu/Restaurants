package challenge.android.feature.restaurants.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import challenge.android.feature.restaurants.presentation.Fakes.RESTAURANT_1
import challenge.android.feature.restaurants.presentation.Fakes.RESTAURANT_3
import challenge.android.feature.restaurants.presentation.Fakes.RESTAURANT_LIST
import challenge.android.feature.restaurants.presentation.mapper.toUiModel
import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import challenge.android.testutils.BaseTest
import challenge.android.testutils.TestCoroutineRule
import challenge.feature.restaurants.domain.SortType.DELIVERY_COST
import challenge.feature.restaurants.usecase.FilterRestaurantsUseCase
import challenge.feature.restaurants.usecase.GetRestaurantListUseCase
import challenge.feature.restaurants.usecase.SortRestaurantsUseCase
import io.mockk.Called
import io.mockk.clearAllMocks
import io.mockk.clearMocks
import io.mockk.clearStaticMockk
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

private const val AN_ERROR = "an error occurred"

@RunWith(JUnit4::class)
class RestaurantsViewModelTest : BaseTest() {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var getRestaurantListUseCase: GetRestaurantListUseCase

    @RelaxedMockK
    private lateinit var sortRestaurantsUseCase: SortRestaurantsUseCase

    @RelaxedMockK
    private lateinit var filterRestaurantsUseCase: FilterRestaurantsUseCase

    @RelaxedMockK
    private lateinit var showProgressObserver: Observer<Boolean>

    @RelaxedMockK
    private lateinit var restaurantsObserver: Observer<List<RestaurantUiModel>>

    @RelaxedMockK
    private lateinit var errorObserver: Observer<String>

    @RelaxedMockK
    private lateinit var showRestaurantsListObserver: Observer<Boolean>

    @InjectMockKs
    private lateinit var viewModel: RestaurantsViewModel

    override fun setUp() {
        super.setUp()
        viewModel.showProgress().observeForever(showProgressObserver)
        viewModel.error().observeForever(errorObserver)
        viewModel.restaurants().observeForever(restaurantsObserver)
        viewModel.showRestaurantsList().observeForever(showRestaurantsListObserver)
        coEvery { getRestaurantListUseCase.invoke() } returns RESTAURANT_LIST
        // For simplicity return the same list. We have already tested the filtering and sorting
        // logics in domain level itself.
        coEvery { sortRestaurantsUseCase.invoke(any(), any()) } returns RESTAURANT_LIST
        coEvery { filterRestaurantsUseCase.invoke(any(), any()) } returns RESTAURANT_LIST

    }

    @After
    fun tearDown() {
        viewModel.showProgress().removeObserver(showProgressObserver)
        viewModel.error().removeObserver(errorObserver)
        viewModel.restaurants().removeObserver(restaurantsObserver)
        viewModel.showRestaurantsList().removeObserver(showRestaurantsListObserver)
        clearAllMocks()
        clearStaticMockk()
    }

    @Test
    fun `should handle progress bar when restaurant list requested`() =
        testCoroutineRule.runBlockingTest {
            viewModel.makeInitialRestaurantsRequest()

            coVerifyOrder {
                showProgressObserver.onChanged(true)
                getRestaurantListUseCase.invoke()
                showProgressObserver.onChanged(false)
            }
        }

    @Test
    fun `should show error when error happened`() = testCoroutineRule.runBlockingTest {
        coEvery { getRestaurantListUseCase.invoke() } throws Exception(AN_ERROR)

        viewModel.makeInitialRestaurantsRequest()

        verify { errorObserver.onChanged(AN_ERROR) }
    }

    @Test
    fun `should call getRestaurants only for the initial request`() {
        viewModel.makeInitialRestaurantsRequest()
        coVerify { getRestaurantListUseCase.invoke() }

        clearMocks(getRestaurantListUseCase)
        // Second call - for activity recreation flow test
        viewModel.makeInitialRestaurantsRequest()
        verify { getRestaurantListUseCase wasNot Called }
    }

    @Test
    fun `should update restaurant list with default sorted when server returns data`() =
        testCoroutineRule.runBlockingTest {
            viewModel.makeInitialRestaurantsRequest()

            coVerifyOrder {
                showProgressObserver.onChanged(true)
                getRestaurantListUseCase.invoke()
                sortRestaurantsUseCase.invoke(RESTAURANT_LIST)
                restaurantsObserver.onChanged(RESTAURANT_LIST.toUiModel())
                showRestaurantsListObserver.onChanged(true)
                showProgressObserver.onChanged(false)

            }
        }

    @Test
    fun `should sort the restaurants when sort value changed`() {
        viewModel.makeInitialRestaurantsRequest() // should have the initial data before sorting

        viewModel.onSortOptionSelected(DELIVERY_COST)

        verify { sortRestaurantsUseCase.invoke(RESTAURANT_LIST, DELIVERY_COST) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should filter the restaurants for given name value`() = testCoroutineRule.runBlockingTest {
        viewModel.makeInitialRestaurantsRequest() // should have the initial data before filtering
        every { filterRestaurantsUseCase.invoke(any(), any()) } returns listOf(
            RESTAURANT_1,
            RESTAURANT_3
        )

        viewModel.onNameFilterChanged("Pi")

        advanceTimeBy(500)
        verify { restaurantsObserver.onChanged(listOf(RESTAURANT_1, RESTAURANT_3).toUiModel()) }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should perform the filtering on the correct list when filtered by name`() =
        testCoroutineRule.runBlockingTest {
            // should have the initial data before filtering
            // After this call, the source will be original list i.e Fake.RESTAURANT_LIST
            viewModel.makeInitialRestaurantsRequest()
            val initialFilteredList = listOf(
                RESTAURANT_1,
                RESTAURANT_3
            )
            every { filterRestaurantsUseCase.invoke(any(), any()) } returns initialFilteredList

            //Case #1
            viewModel.onNameFilterChanged("Pizza")

            advanceTimeBy(500)
            verify { filterRestaurantsUseCase.invoke(RESTAURANT_LIST, "Pizza") }

            //Case #2: Now the user deletes the character
            viewModel.onNameFilterChanged("Piz")

            advanceTimeBy(500)
            verify { filterRestaurantsUseCase.invoke(RESTAURANT_LIST, "Piz") }

            //Case #3: Now the user adds the character
            viewModel.onNameFilterChanged("Pizz")
            advanceTimeBy(500)
            verify { filterRestaurantsUseCase.invoke(initialFilteredList, "Pizz") }

        }
}

