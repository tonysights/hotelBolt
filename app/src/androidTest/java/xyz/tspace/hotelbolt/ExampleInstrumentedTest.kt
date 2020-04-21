package xyz.tspace.hotelbolt

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.tspace.hotelbolt.base.BaseBean
import xyz.tspace.hotelbolt.base.ResponseUnit
import xyz.tspace.hotelbolt.http.api.ImageService
import xyz.tspace.hotelbolt.model.Pixabay

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {

        // Context of the app under test.
        //val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        //assertEquals("xyz.tspace.hotelbolt", appContext.packageName)
        testImage()
    }

    fun testImage() {

    }

}
