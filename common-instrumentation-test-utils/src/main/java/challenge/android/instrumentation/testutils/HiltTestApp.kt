package challenge.android.instrumentation.testutils

import challenge.android.common.BaseApp
import dagger.hilt.android.testing.CustomTestApplication

@CustomTestApplication(BaseApp::class)
interface HiltTestApp
