package challenge.android.feature.restaurants.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import challenge.android.common.utils.BaseViewModel
import challenge.android.feature.restaurants.presentation.mapper.toUiModel
import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.SortType
import challenge.feature.restaurants.usecase.FilterRestaurantsUseCase
import challenge.feature.restaurants.usecase.GetRestaurantListUseCase
import challenge.feature.restaurants.usecase.SortRestaurantsUseCase
import challenge.feature.restaurants.usecase.SortRestaurantsUseCase.Companion.DEFAULT_SORT_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val KEY_SELECTED_SORTED = "selected_sort"

@VisibleForTesting
internal const val KEY_NAME_FILTER = "name_filter"

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    private val getRestaurantList: GetRestaurantListUseCase,
    private val sortRestaurantsUseCase: SortRestaurantsUseCase,
    private val filterRestaurantsUseCase: FilterRestaurantsUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val displayableRestaurants =
        MediatorLiveData<List<RestaurantUiModel>>() // restaurants which bind to UI

    // The UI equivalent domain object, should be in sync with ui(displayable) models above, we will do all logics in domain objects.
    // Once the logic is done, simply we will convert it to UI model and will update the UI
    private var restaurants = MutableLiveData<List<Restaurant>>()
    private val showRestaurantsList = MutableLiveData<Boolean>()
    private val showNoResults = MutableLiveData<Boolean>()
    private val selectedSort = savedStateHandle.getLiveData(KEY_SELECTED_SORTED, DEFAULT_SORT_TYPE)

    private var sourceRestaurants =
        emptyList<Restaurant>() // back up field, used to restore the original when filter is cleared
    private var filterJob: Job? = null
    private var isInitialRequestMade = false // to handle activity recreation flow
    val sortingOptions = SortType.values()

    init {
        displayableRestaurants.addSource(restaurants) { // link the domain and UI model
            showNoResults.value = it.isEmpty()
            showRestaurantsList.value = it.isNotEmpty()
            displayableRestaurants.value = it.toUiModel()
        }
    }

    fun nameFilterValue() = savedStateHandle.get<String>(KEY_NAME_FILTER) ?: ""

    fun makeInitialRestaurantsRequest() {
        if (isInitialRequestMade.not()) {
            viewModelScope.launch {
                try {
                    showProgress.value = true
                    sourceRestaurants =
                        sortRestaurantsUseCase(getRestaurantList(), selectedSort.value)
                    // On process death nameFilterValue will not be empty here
                    if (nameFilterValue().isBlank()) {
                        restaurants.value = sourceRestaurants
                    } else {
                        restaurants.value =
                            filterRestaurantsUseCase(sourceRestaurants, nameFilterValue())
                    }
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
            if (isCharacterDeleted(newNameFilter) || newNameFilter.isBlank()) { // deletion or char clear case => can't rely on the filtered value. Do the filter on the source list
                restaurants.value = filterRestaurantsUseCase(sourceRestaurants, newNameFilter)
            } else { // new char added - case => do the filtering in already filtered value
                restaurants.value =
                    restaurants.value?.let { filterRestaurantsUseCase(it, newNameFilter) }
            }
            savedStateHandle.set(KEY_NAME_FILTER, newNameFilter)
        }
    }

    private fun isCharacterDeleted(newNameFilter: String) =
        nameFilterValue().length > newNameFilter.length

    fun onSortOptionSelected(sortType: SortType) {
        selectedSort.value = sortType
        restaurants.value = restaurants.value?.let { sortRestaurantsUseCase(it, sortType) }
        sourceRestaurants =
            sortRestaurantsUseCase(sourceRestaurants, sortType) // Update the backup field
    }

    fun restaurants(): LiveData<List<RestaurantUiModel>> = displayableRestaurants
    fun showRestaurantsList(): LiveData<Boolean> = showRestaurantsList
    fun showNoResults(): LiveData<Boolean> = showNoResults
    fun selectedSort(): LiveData<SortType> = selectedSort
}
