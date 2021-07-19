package challenge.android.feature.restaurants.presentation.ui

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import challenge.android.common.extensions.bind
import challenge.android.common.extensions.bindVisibility
import challenge.android.feature.restaurants.presentation.R
import challenge.android.feature.restaurants.presentation.databinding.ActivityRestaurantListBinding
import challenge.android.feature.restaurants.presentation.viewmodel.RestaurantsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantListBinding
    private lateinit var restaurantsAdapter: RestaurantsAdapter
    private val viewModel: RestaurantsViewModel by viewModels()
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_list)
        setupRecyclerView()
        setupObservers()
        viewModel.makeInitialRestaurantsRequest()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.restarurant_list, menu)
        setupNameSearchMenuOption(menu)
        return true
    }

    private fun setupNameSearchMenuOption(menu: Menu) {
        val myActionMenuItem = menu.findItem(R.id.actionSearch)
        val searchView = myActionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filterRestaurantsByName(newText)
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
                restaurantsAdapter.submitList(it)
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
