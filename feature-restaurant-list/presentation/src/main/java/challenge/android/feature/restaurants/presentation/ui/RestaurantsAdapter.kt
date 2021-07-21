package challenge.android.feature.restaurants.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import challenge.android.feature.restaurants.presentation.databinding.RowItemRestuarantBinding
import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel

private val diffCallback = object : DiffUtil.ItemCallback<RestaurantUiModel>() {
    override fun areItemsTheSame(old: RestaurantUiModel, new: RestaurantUiModel) = old.id == new.id
    override fun areContentsTheSame(old: RestaurantUiModel, new: RestaurantUiModel) = old == new
}

class RestaurantsAdapter :
    ListAdapter<RestaurantUiModel, RestaurantsAdapter.RestaurantViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RestaurantViewHolder(
            RowItemRestuarantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) =
        holder.bind(getItem(position))

    class RestaurantViewHolder(
        private val binding: RowItemRestuarantBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: RestaurantUiModel) {
            binding.apply {
                tvName.text = restaurant.name
                tvStatus.text = restaurant.status
                tvRating.text = restaurant.rating.toString()
                tvMatchScore.text = restaurant.matchScore.toString()
                tvNewestScore.text = restaurant.newScaleScore.toString()
                tvDistance.text = restaurant.distance
                tvPopularity.text = restaurant.popularityScore.toString()
                tvMinPrice.text = restaurant.minimumCost
                tvDeliveryCost.text = restaurant.deliveryCost
                tvPrice.text = restaurant.averagePrice
            }
        }
    }
}
