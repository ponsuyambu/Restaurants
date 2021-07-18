package challenge.android.common.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.facebook.shimmer.ShimmerFrameLayout

/**
 * Binds the visibility of any view with the given live data.
 * @param onVisible will be invoked when the item is visible to do any custom actions
 */
fun View.bindVisibility(
    liveData: LiveData<Boolean>,
    lifecycleOwner: LifecycleOwner,
    onVisible: (() -> Unit)? = null
) {
    liveData.observe(
        lifecycleOwner,
        {
            visibility = if (it != null && it == true) {
                onVisible?.invoke()
                VISIBLE
            } else {
                GONE
            }
        }
    )
}

/**
 * Binds the visibility of the textview with the given live data.
 * @param onVisible will be invoked when the item is visible to do any custom actions
 */
fun TextView.bindVisibility(
    liveData: LiveData<String?>,
    lifecycleOwner: LifecycleOwner,
    onVisible: ((String) -> Unit)? = null
) {
    liveData.observe(
        lifecycleOwner,
        {
            visibility = if (it != null) {
                onVisible?.invoke(it)
                VISIBLE
            } else {
                GONE
            }
        }
    )
}

/**
 * Binds Shimmer
 */
fun ShimmerFrameLayout.bind(
    liveData: LiveData<Boolean>,
    lifecycleOwner: LifecycleOwner
) {
    liveData.observe(
        lifecycleOwner
    ) {
        visibility = if (it != null && it == true) {
            startShimmer()
            VISIBLE
        } else {
            stopShimmer()
            GONE
        }
    }
}
