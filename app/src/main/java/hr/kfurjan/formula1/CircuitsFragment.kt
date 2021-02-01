package hr.kfurjan.formula1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mancj.materialsearchbar.MaterialSearchBar
import dagger.hilt.android.AndroidEntryPoint
import hr.kfurjan.formula1.databinding.FragmentCircuitsBinding
import hr.kfurjan.formula1.model.enum.CircuitSpinnerOptions
import hr.kfurjan.formula1.viewmodel.CircuitViewModel

@AndroidEntryPoint
class CircuitsFragment : Fragment() {

    private var _binding: FragmentCircuitsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: CircuitViewModel by activityViewModels()
    private lateinit var circuitAdapter: CircuitAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCircuitsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HostActivity).supportActionBar?.title = getString(R.string.circuits)

        initSpinner()
        initAdapter()
        initSearchListeners()
    }

    private fun initSpinner() =
        binding.circuitSpinner.setItems(
            resources.getStringArray(R.array.circuit_spinner_values).toMutableList()
        )

    private fun initAdapter() {
        circuitAdapter = CircuitAdapter(requireContext())
        binding.rvCircuits.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = circuitAdapter
        }

        viewModel.getCircuitsData().observe(
            viewLifecycleOwner, { circuits -> circuitAdapter.setCircuits(circuits) }
        )
    }

    private fun initSearchListeners() {
        binding.circuitSearchBar.setOnSearchActionListener(object :
                MaterialSearchBar.OnSearchActionListener {
                override fun onSearchConfirmed(text: CharSequence?) {
                    when (binding.circuitSpinner.selectedIndex) {
                        CircuitSpinnerOptions.NAME.ordinal -> viewModel.filterCircuitsByName(text.toString())
                        CircuitSpinnerOptions.COUNTRY.ordinal -> viewModel.filterCircuitsByCountry(text.toString())
                    }
                }

                override fun onSearchStateChanged(enabled: Boolean) {}
                override fun onButtonClicked(buttonCode: Int) {}
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
