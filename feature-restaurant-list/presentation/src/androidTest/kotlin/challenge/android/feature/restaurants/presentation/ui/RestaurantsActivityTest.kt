package challenge.android.feature.restaurants.presentation.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import challenge.android.common.utils.SingleLiveEvent
import challenge.android.feature.restaurants.presentation.R
import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import challenge.android.feature.restaurants.presentation.viewmodel.RestaurantsViewModel
import challenge.android.instrumentation.testutils.lazyActivityScenarioRule
import com.google.android.material.R.id
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertContains
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.spyk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val AN_ERROR_MESSAGE = "an error"
private val RESTAURANT_1 = RestaurantUiModel("Restaurant 1", 4.5f)
private val RESTAURANT_2 = RestaurantUiModel("Restaurant 2", 4f)

@HiltAndroidTest
class RestaurantsActivityTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = lazyActivityScenarioRule<RestaurantsActivity>(launchActivity = false)

    @BindValue
    @RelaxedMockK
    lateinit var viewModel: RestaurantsViewModel

    private lateinit var showProgress: MutableLiveData<Boolean>
    private lateinit var showRestaurantsList: MutableLiveData<Boolean>
    private lateinit var error: SingleLiveEvent<String>
    private lateinit var restaurants: MutableList<RestaurantUiModel>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        setupData()
        setupViewModel()
        hiltRule.inject()
        // Once all the dependencies are set, start the activity
        activityRule.launch()
        activityRule.getScenario().moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun shouldRequestRestaurantList_When_ScreenIsShown() {
        verify { viewModel.requestRestaurants() }
    }

    @Test
    fun shouldAddSubscribers_When_ScreenIsShown() {
        activityRule.getScenario().onActivity {
            verify { showProgress.observe(it, any()) }
            verify { error.observe(it, any()) }
            verify { showRestaurantsList.observe(it, any()) }
        }
    }

    @Test
    fun shouldShowProgress_WTO_LiveDataSubject() {
        /*** Case 1 ***/
        showProgress.postValue(true)
        assertDisplayed(R.id.progressShimmer)

        /*** Case 2 ***/
        showProgress.postValue(false)
        assertNotDisplayed(R.id.progressShimmer)
    }

    @Test
    fun shouldShowError_WRTo_LiveDataSubject() {
        error.postValue(AN_ERROR_MESSAGE)

        assertDisplayed(id.snackbar_text)
        assertContains(id.snackbar_text, AN_ERROR_MESSAGE)
    }

    @Test
    fun shouldDisplayRestaurantsList_With_TheReceivedData() {
        showRestaurantsList.postValue(true)

        assertRestaurantListWithData(restaurants)
    }

    private fun assertRestaurantListWithData(data: List<RestaurantUiModel>) {
        BaristaRecyclerViewAssertions.assertRecyclerViewItemCount(R.id.listRestaurants, data.size)
        data.forEachIndexed { index, restaurantUiModel ->
            assertDisplayedRestaurantListItem(index, restaurantUiModel)
        }
    }

    private fun assertDisplayedRestaurantListItem(
        position: Int,
        restaurant: RestaurantUiModel
    ) {
        assertDisplayedAtPosition(
            R.id.listRestaurants,
            position,
            R.id.tvName,
            restaurant.name
        )
        assertDisplayedAtPosition(
            R.id.listRestaurants,
            position,
            R.id.tvRating,
            restaurant.rating.toString()
        )
    }

    private fun setupData() {
        showProgress = spyk()
        error = spyk()
        showRestaurantsList = spyk()
        restaurants = mutableListOf(RESTAURANT_1, RESTAURANT_2)
    }

    private fun setupViewModel() {
        every { viewModel.showProgress() } returns showProgress
        every { viewModel.error() } returns error
        every { viewModel.showRestaurantsList() } returns showRestaurantsList
        every { viewModel.restaurants() } returns restaurants
    }
}
