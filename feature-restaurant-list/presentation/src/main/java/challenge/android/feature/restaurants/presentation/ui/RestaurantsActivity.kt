package challenge.android.feature.restaurants.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_list)
        setupRecyclerView()
        setupObservers()
        viewModel.requestRestaurants()
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
        binding.listRestaurants.bindVisibility(viewModel.showRestaurantsList(), this)
    }

    private fun setupRecyclerView() {
        restaurantsAdapter = RestaurantsAdapter(viewModel.restaurants())
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
