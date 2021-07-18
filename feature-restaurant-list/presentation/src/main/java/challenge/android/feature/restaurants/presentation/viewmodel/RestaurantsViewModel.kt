package challenge.android.feature.restaurants.presentation.viewmodel

import challenge.android.common.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor() : BaseViewModel() {
    fun requestRestaurants() {
    }
}
