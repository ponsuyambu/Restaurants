package challenge.android.common.utils

import androidx.test.espresso.idling.CountingIdlingResource

interface AppIdlingResource {
    fun increment()
    fun decrement()
}

object EspressoIdlingResource : AppIdlingResource {
    private const val RESOURCE = "GLOBAL"

    @JvmField
    val countingIdlingResource = CountingIdlingResource(RESOURCE)

    override fun increment() = countingIdlingResource.increment()

    override fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}
