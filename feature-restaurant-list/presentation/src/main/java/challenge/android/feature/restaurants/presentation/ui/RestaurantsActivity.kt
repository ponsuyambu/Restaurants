package challenge.android.feature.restaurants.presentation.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import challenge.android.common.extensions.bind
import challenge.android.common.extensions.bindVisibility
import challenge.android.feature.restaurants.presentation.R
import challenge.android.feature.restaurants.presentation.databinding.ActivityRestaurantListBinding
import challenge.android.feature.restaurants.presentation.mapper.displayIcon
import challenge.android.feature.restaurants.presentation.mapper.displayName
import challenge.android.feature.restaurants.presentation.viewmodel.RestaurantsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantListBinding
    private lateinit var restaurantsAdapter: RestaurantsAdapter
    private val viewModel: RestaurantsViewModel by viewModels()
    private lateinit var searchView: SearchView
    private lateinit var searchMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_list)
        setupRecyclerView()
        setupObservers()
        viewModel.makeInitialRestaurantsRequest()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.restarurant_list, menu)
        viewModel.sortingOptions.forEach { sortType ->
            menu.findItem(R.id.actionSort).subMenu.add(
                R.id.sortGroup,
                sortType.ordinal,
                sortType.ordinal,
                sortType.displayName()
            ).apply {
                icon =
                    AppCompatResources.getDrawable(this@RestaurantsActivity, sortType.displayIcon())
            }
        }
        menu.findItem(R.id.actionSort).subMenu.setGroupCheckable(R.id.sortGroup, true, true)
        viewModel.selectedSort().observe(this) {
            menu.findItem(it.ordinal).isChecked = true
        }
        setupNameSearchMenuOption(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.groupId == R.id.sortGroup) {
            viewModel.onSortOptionSelected(viewModel.sortingOptions[item.itemId])
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupNameSearchMenuOption(menu: Menu) {
        searchMenuItem = menu.findItem(R.id.actionSearch)
        searchView = searchMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.onNameFilterChanged(newText)
                return true
            }
        })
    }

    private fun setupObservers() {
        binding.progressShimmer.bind(viewModel.showProgress(), this)
        viewModel.error().observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
                .setAction("CLOSE") {
                    // NO OP, on click of the action, it will be dismissed by default.
                }
                .show()
        }
        viewModel.restaurants().observe(this) {
            it?.let {
                restaurantsAdapter.submitList(it) {
                    binding.listRestaurants.scrollToPosition(0)
                }
            }
        }
        binding.listRestaurants.bindVisibility(viewModel.showRestaurantsList(), this)
    }

    private fun setupRecyclerView() {
        restaurantsAdapter = RestaurantsAdapter()
        binding.listRestaurants.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
            adapter = restaurantsAdapter
        }
    }
}
