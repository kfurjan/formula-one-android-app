package hr.algebra.formula1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.driver_options_array,
            R.layout.support_simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            binding.driverSpinner.adapter = adapter
        }
    }

    private fun initDriverAdapter() {
        driverAdapter = DriverAdapter()
        binding.rvDrivers.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = driverAdapter
        }.also { driverAdapter.setDrivers(viewModel.getDrivers()) }
    }

    private fun initSearchListeners() {
        binding.driverSearchBar.setOnSearchActionListener(object :
                MaterialSearchBar.OnSearchActionListener {
                override fun onSearchStateChanged(enabled: Boolean) {}

                override fun onSearchConfirmed(text: CharSequence?) {
                    when (binding.driverSpinner.selectedItem.toString()) {
                        getString(R.string.driver_spinner_name) -> driverAdapter.setDrivers(
                            viewModel.filterDriversByName(text.toString())
                        )
                        getString(R.string.driver_spinner_last_name) -> driverAdapter.setDrivers(
                            viewModel.filterDriversByLastName(text.toString())
                        )
                        getString(R.string.driver_spinner_nationality) -> driverAdapter.setDrivers(
                            viewModel.filterDriversByNationality(text.toString())
                        )
                    }
                }

                override fun onButtonClicked(buttonCode: Int) {}
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
