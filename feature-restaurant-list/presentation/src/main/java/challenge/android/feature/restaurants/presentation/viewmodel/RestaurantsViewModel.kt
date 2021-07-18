package challenge.android.feature.restaurants.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import challenge.android.common.utils.BaseViewModel
import challenge.android.feature.restaurants.presentation.mapper.toUiModel
import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import challenge.feature.restaurants.usecase.GetRestaurantListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(val getRestaurantList: GetRestaurantListUseCase) :
    BaseViewModel() {

    private val restaurants = mutableListOf<RestaurantUiModel>()
    private val showRestaurantsList = MutableLiveData<Boolean>()

    fun requestRestaurants() {
        viewModelScope.launch {
            try {
                showProgress.value = true
                restaurants.addAll(getRestaurantList().toUiModel())
                showRestaurantsList.postValue(true)
                showProgress.value = false
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }

    fun restaurants(): List<RestaurantUiModel> = restaurants
    fun showRestaurantsList(): LiveData<Boolean> = showRestaurantsList
}
