package challenge.android.common.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    protected val showProgress = MutableLiveData(false)
    protected val error = SingleLiveEvent<String>()

    fun showProgress(): LiveData<Boolean> = showProgress
    fun error(): LiveData<String> = error
}
