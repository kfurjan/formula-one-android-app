package hr.algebra.formula1

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.formula1.databinding.ActivitySplashScreenBinding
import hr.algebra.formula1.extensions.applyAnimation
import hr.algebra.formula1.extensions.startActivity

private const val DELAY: Long = 2600
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

    private fun redirect() =
        Handler(Looper.getMainLooper()).postDelayed(
            { startActivity<HostActivity>() }, DELAY
        )
}