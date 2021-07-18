package challenge.android.feature.restaurants.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import challenge.android.common.utils.BaseViewModel
import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor() : BaseViewModel() {

    private val restaurants = mutableListOf<RestaurantUiModel>()
    private val showRestaurantsList = MutableLiveData<Boolean>()

    fun requestRestaurants() {}

    fun restaurants(): List<RestaurantUiModel> = restaurants
    fun showRestaurantsList(): LiveData<Boolean> = showRestaurantsList
}
