package challenge.feature.restaurants.framework.di

import android.content.Context
import challenge.feature.restaurants.data.service.RestaurantService
import challenge.feature.restaurants.framework.RestaurantServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FrameworkModule {
    @Provides
    @Singleton
    internal fun provideRestaurantService(@ApplicationContext context: Context): RestaurantService =
        RestaurantServiceImpl(context)
}