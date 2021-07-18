package challenge.android.feature.restaurants.presentation.ui

import androidx.lifecycle.Lifecycle
import challenge.android.feature.restaurants.presentation.viewmodel.RestaurantsViewModel
import challenge.android.instrumentation.testutils.lazyActivityScenarioRule
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class RestaurantsActivityTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = lazyActivityScenarioRule<RestaurantsActivity>(launchActivity = false)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @BindValue
    @RelaxedMockK
    lateinit var viewModel: RestaurantsViewModel

    @Test
    fun shouldRequestRestaurantList_When_ScreenShown() {
        activityRule.launch()
        activityRule.getScenario().moveToState(Lifecycle.State.RESUMED)
        verify { viewModel.requestRestaurants() }
    }
}
