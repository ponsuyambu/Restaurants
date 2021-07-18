package challenge.android.common

import android.app.Application
import timber.log.Timber

//  Why do we need this base class?
// workaround fix to enable instrument testing with hilt support => https://stackoverflow.com/questions/62927037/customtestapplication-value-cannot-be-annotated-with-hiltandroidapp
open class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
