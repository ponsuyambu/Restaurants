package challenge.android.testutils

import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
open class BaseTest {
    @Before
    open fun setUp() {
        MockKAnnotations.init(this)
    }
}
