package challenge.android.feature.restaurants.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import challenge.android.common.utils.BaseViewModel
import challenge.android.feature.restaurants.presentation.mapper.toUiModel
import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.SortType
import challenge.feature.restaurants.usecase.FilterRestaurantsUseCase
import challenge.feature.restaurants.usecase.GetRestaurantListUseCase
import challenge.feature.restaurants.usecase.SortRestaurantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    val getRestaurantList: GetRestaurantListUseCase,
    val sortRestaurantsUseCase: SortRestaurantsUseCase,
    val filterRestaurantsUseCase: FilterRestaurantsUseCase,
) : BaseViewModel() {
    private val displayableRestaurants =
        MediatorLiveData<List<RestaurantUiModel>>() // restaurants which bind to UI

    // The UI equivalent domain object, should be in sync with ui(displayable) models above, we will do all logics in domain objects.
    // Once the logic is done, simply we will convert it to UI model and will update the UI
    private var restaurants = MutableLiveData<List<Restaurant>>()
    private val showRestaurantsList = MutableLiveData<Boolean>()
    private val selectedSort = MutableLiveData<SortType>()

    private var sourceRestaurants =
        emptyList<Restaurant>() // back up field, used to restore the original when filter is cleared
    private var filterJob: Job? = null
    private var isInitialRequestMade = false // to handle activity recreation flow
    private var nameFilterValue = ""
    val sortingOptions = SortType.values()

    init {
        displayableRestaurants.addSource(restaurants) { // link the domain and UI model
            displayableRestaurants.value = it.toUiModel()
        }
    }

    fun makeInitialRestaurantsRequest() {
        if (isInitialRequestMade.not()) {
            viewModelScope.launch {
                try {
                    showProgress.value = true
                    sourceRestaurants = sortRestaurantsUseCase(getRestaurantList())
                    selectedSort.value = SortRestaurantsUseCase.DEFAULT_SORT_TYPE
                    restaurants.value = sourceRestaurants
                    showRestaurantsList.postValue(true)
                    showProgress.value = false
                } catch (e: Exception) {
                    error.value = e.message
                }
            }
        }
        isInitialRequestMade = true
    }

    fun onNameFilterChanged(name: String) {
        filterJob?.cancel()
        filterJob = viewModelScope.launch {
            delay(500) // throttle the user input for 500ms
            val newNameFilter = name.trim()
            // Performance optimization
            if (isCharacterDeleted(newNameFilter)) { // deletion or char clear case => can't rely on the filtered value. Do the filter on the source list
                restaurants.value = filterRestaurantsUseCase(sourceRestaurants, newNameFilter)
            } else { // new char added - case => do the filtering in already filtered value
                restaurants.value =
                    restaurants.value?.let { filterRestaurantsUseCase(it, newNameFilter) }
            }
            nameFilterValue = newNameFilter
        }
    }

    private fun isCharacterDeleted(newNameFilter: String) =
        nameFilterValue.length > newNameFilter.length

    fun onSortOptionSelected(sortType: SortType) {
        selectedSort.value = sortType
        restaurants.value = restaurants.value?.let { sortRestaurantsUseCase(it, sortType) }
        sourceRestaurants =
            sortRestaurantsUseCase(sourceRestaurants, sortType) // Update the backup field
    }

    fun restaurants(): LiveData<List<RestaurantUiModel>> = displayableRestaurants
    fun showRestaurantsList(): LiveData<Boolean> = showRestaurantsList
    fun selectedSort(): LiveData<SortType> = selectedSort
}
