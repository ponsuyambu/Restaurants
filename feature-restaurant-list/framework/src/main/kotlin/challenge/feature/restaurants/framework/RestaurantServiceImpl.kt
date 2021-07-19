package challenge.feature.restaurants.framework

import android.content.Context
import challenge.feature.restaurants.data.service.RestaurantService
import challenge.module.framework.android.restaurants.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext


class RestaurantServiceImpl constructor(private val context: Context) : RestaurantService {
    override suspend fun getRestaurants(): String = coroutineScope {
        withContext(Dispatchers.IO) {
            val responseStream = context.resources.openRawResource(R.raw.restaurants_response)
            return@withContext responseStream.bufferedReader().use { it.readText() }
        }
    }
}