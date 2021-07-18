package challenge.android.common.di

import challenge.android.common.utils.AppIdlingResource
import challenge.android.common.utils.EspressoIdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {
    @Provides
    internal fun provideIdlingResource(): AppIdlingResource = EspressoIdlingResource
}
