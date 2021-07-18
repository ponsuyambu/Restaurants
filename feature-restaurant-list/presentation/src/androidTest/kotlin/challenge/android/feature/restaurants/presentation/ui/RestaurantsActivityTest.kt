package challenge.android.feature.restaurants.presentation.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import challenge.android.common.utils.SingleLiveEvent
import challenge.android.feature.restaurants.presentation.R
import challenge.android.feature.restaurants.presentation.viewmodel.RestaurantsViewModel
import challenge.android.instrumentation.testutils.lazyActivityScenarioRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions
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

@HiltAndroidTest
class RestaurantsActivityTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = lazyActivityScenarioRule<RestaurantsActivity>(launchActivity = false)

    private lateinit var showProgress: MutableLiveData<Boolean>
    private lateinit var error: SingleLiveEvent<String>

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

    private fun setupData() {
        showProgress = spyk()
        error = spyk()
    }

    private fun setupViewModel() {
        every { viewModel.showProgress() } returns showProgress
        every { viewModel.error() } returns error
    }

    @BindValue
    @RelaxedMockK
    lateinit var viewModel: RestaurantsViewModel

    @Test
    fun shouldRequestRestaurantList_When_ScreenIsShown() {
        verify { viewModel.requestRestaurants() }
    }

    @Test
    fun shouldAddSubscribers_When_ScreenIsShown() {
        activityRule.getScenario().onActivity {
            verify { showProgress.observe(it, any()) }
            verify { error.observe(it, any()) }
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

        assertDisplayed(com.google.android.material.R.id.snackbar_text)
        BaristaVisibilityAssertions.assertContains(
            com.google.android.material.R.id.snackbar_text,
            AN_ERROR_MESSAGE
        )
    }
}
