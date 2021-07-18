package challenge.android.feature.restaurants.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import challenge.android.common.utils.BaseViewModel
import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor() : BaseViewModel() {

    private val restaurants = mutableListOf<RestaurantUiModel>()
    private val showRestaurantsList = MutableLiveData<Boolean>()

    fun requestRestaurants() {
        viewModelScope.launch {
            showProgress.value = true
            delay(3000)
            restaurants.apply {
                add(RestaurantUiModel("Restaurant 1", 4f))
                add(RestaurantUiModel("Restaurant 2", 3f))
            }
            showRestaurantsList.postValue(true)
            showProgress.value = false
        }
    }

    fun restaurants(): List<RestaurantUiModel> = restaurants
    fun showRestaurantsList(): LiveData<Boolean> = showRestaurantsList
}
