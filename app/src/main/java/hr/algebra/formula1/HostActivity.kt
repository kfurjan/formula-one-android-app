package hr.algebra.formula1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.formula1.databinding.ActivityHostBinding

private lateinit var binding: ActivityHostBinding

class HostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
