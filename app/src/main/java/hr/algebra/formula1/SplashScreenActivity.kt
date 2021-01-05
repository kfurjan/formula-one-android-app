package hr.algebra.formula1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.formula1.databinding.ActivitySplashScreenBinding
import hr.algebra.formula1.extensions.applyAnimation
import hr.algebra.formula1.extensions.getBooleanPreference
import hr.algebra.formula1.extensions.isOnline
import hr.algebra.formula1.extensions.startActivity

private const val DELAY: Long = 2600
const val DATA_IMPORTED: String = "hr.algebra.formula1.data_imported"

private lateinit var binding: ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startAnimations()
        redirect()
    }

    private fun startAnimations() = binding.ivSplash.applyAnimation(R.anim.slide_in_out)

    private fun redirect() {
        if (getBooleanPreference(DATA_IMPORTED)) {
            Handler(Looper.getMainLooper()).postDelayed(
                { startActivity<HostActivity>() }, DELAY
            )
        } else {
            if (isOnline()) {
                Intent(this, Formula1DataService::class.java).apply {
                    Formula1DataService.enqueueWork(this@SplashScreenActivity, this)
                }
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.please_connect_to_the_internet),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }
}
