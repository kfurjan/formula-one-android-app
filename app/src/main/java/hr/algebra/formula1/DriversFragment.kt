package hr.algebra.formula1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mancj.materialsearchbar.MaterialSearchBar
import hr.algebra.formula1.databinding.FragmentDriversBinding
import hr.algebra.formula1.viewmodel.DriverViewModel

class DriversFragment : Fragment() {

    private var _binding: FragmentDriversBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DriverViewModel by viewModels()

    private lateinit var driverAdapter: DriverAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDriversBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HostActivity).supportActionBar?.title = getString(R.string.drivers)

        initSpinner()
        initDriverAdapter()
        initSearchListeners()
    }

    private fun initSpinner() =
        binding.driverSpinner.setItems(
            resources.getStringArray(R.array.driver_options_array).toMutableList()
        )

    private fun initDriverAdapter() {
        driverAdapter = DriverAdapter()
        binding.rvDrivers.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = driverAdapter
        }

        viewModel.getDriversData().observe(
            viewLifecycleOwner,
            { drivers ->
                driverAdapter.setDrivers(drivers)
            }
        )
    }

    private fun initSearchListeners() {
        binding.driverSearchBar.setOnSearchActionListener(object :
                MaterialSearchBar.OnSearchActionListener {
                override fun onSearchStateChanged(enabled: Boolean) { }

                override fun onSearchConfirmed(text: CharSequence?) {
                    when (binding.driverSpinner.selectedIndex) {
                        0 -> viewModel.filterDriversByName(text.toString())
                        1 -> viewModel.filterDriversByLastName(text.toString())
                        2 -> viewModel.filterDriversByNationality(text.toString())
                    }
                }

                override fun onButtonClicked(buttonCode: Int) { }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
