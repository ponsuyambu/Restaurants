package challenge.android.feature.restaurants.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import challenge.android.feature.restaurants.presentation.databinding.RowItemRestuarantBinding
import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel

class RestaurantsAdapter(
    private val restaurants: List<RestaurantUiModel>,
) : RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RestaurantViewHolder(
            RowItemRestuarantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) =
        holder.bind(restaurants[position])

    override fun getItemCount() = restaurants.size

    class RestaurantViewHolder(
        private val binding: RowItemRestuarantBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(uiModel: RestaurantUiModel) {
            binding.tvName.text = uiModel.name
            binding.tvRating.text = uiModel.rating.toString()
        }
    }
}
