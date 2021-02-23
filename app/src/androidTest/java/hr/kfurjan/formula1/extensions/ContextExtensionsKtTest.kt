package hr.kfurjan.formula1.extensions

import android.content.Context
import android.content.res.Configuration
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.lang.IllegalArgumentException

class ContextExtensionsKtTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun shouldBeTrueWhenConnectedToWiFiOrMobileData() = assertThat(context.isOnline()).isTrue()

    @Test
    fun shouldBeTrueIfDarkThemeOrFalseIfDarkThemeOff() =
        when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> assertThat(context.isDarkThemeOn()).isTrue()
            Configuration.UI_MODE_NIGHT_NO -> assertThat(context.isDarkThemeOn()).isFalse()
            else -> throw IllegalArgumentException("Application theme is not defined")
        }
}
