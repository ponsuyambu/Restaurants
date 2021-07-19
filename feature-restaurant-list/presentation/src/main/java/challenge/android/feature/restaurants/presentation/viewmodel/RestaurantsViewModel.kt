package challenge.android.feature.restaurants.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import challenge.android.common.utils.BaseViewModel
import challenge.android.feature.restaurants.presentation.mapper.toUiModel
import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import challenge.android.feature.restaurants.presentation.viewmodel.usecases.FilterRestaurantsByName
import challenge.feature.restaurants.usecase.GetRestaurantListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    val getRestaurantList: GetRestaurantListUseCase,
    val filterRestaurants: FilterRestaurantsByName
) : BaseViewModel() {

    private val restaurants = MutableLiveData<List<RestaurantUiModel>>(emptyList())
    private val originalSourceRestaurants = mutableListOf<RestaurantUiModel>()
    private val showRestaurantsList = MutableLiveData<Boolean>()
    private var filterJob: Job? = null
    private var isInitialRequestMade = false


    fun makeInitialRestaurantsRequest() {
        if (isInitialRequestMade.not()) {
            viewModelScope.launch {
                try {
                    showProgress.value = true
                    originalSourceRestaurants.addAll(getRestaurantList().toUiModel())
                    restaurants.postValue(getRestaurantList().toUiModel())
                    showRestaurantsList.postValue(true)
                    showProgress.value = false
                } catch (e: Exception) {
                    error.value = e.message
                }
            }
        }
        isInitialRequestMade = true

    }

    fun filterRestaurantsByName(name: String) {
        filterJob?.cancel()
        filterJob = viewModelScope.launch {
            delay(500) // throttle the user input for 500ms
            val filteredList = filterRestaurants(originalSourceRestaurants, name)
            restaurants.postValue(filteredList)
        }
    }

    fun restaurants(): LiveData<List<RestaurantUiModel>> = restaurants
    fun showRestaurantsList(): LiveData<Boolean> = showRestaurantsList
}
