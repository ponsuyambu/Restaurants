package challenge.feature.restaurants.data.models

sealed class RawResponseResult<out T> {
    data class Success<out T>(val data: T) : RawResponseResult<T>()
    data class Error(val exception: Throwable) : RawResponseResult<Nothing>()
}
