package hr.kfurjan.formula1

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import hr.kfurjan.formula1.databinding.FragmentAboutBinding
import hr.kfurjan.formula1.extensions.isDarkThemeOn

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as HostActivity).supportActionBar?.title = getString(R.string.about)
        binding.tvAppVersion.text = BuildConfig.VERSION_NAME

        initSharedPreferences()
        intiThemeSwitcher()
    }

    private fun initSharedPreferences() {
        sharedPref = (activity as HostActivity).getSharedPreferences(
            getString(R.string.toggle_state),
            MODE_PRIVATE
        ).also {
            with(it.edit()) {
                putBoolean(getString(R.string.toggle_state), requireContext().isDarkThemeOn())
                apply()
            }
        }
    }

    private fun intiThemeSwitcher() {
        binding.themeSwitch.isChecked = sharedPref.getBoolean(
            getString(R.string.toggle_state),
            requireContext().isDarkThemeOn()
        )

        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    with(sharedPref.edit()) {
                        putBoolean(getString(R.string.toggle_state), true)
                        apply()
                    }
                }
                false -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    with(sharedPref.edit()) {
                        putBoolean(getString(R.string.toggle_state), false)
                        apply()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
