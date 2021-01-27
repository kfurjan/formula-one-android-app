package hr.kfurjan.formula1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mancj.materialsearchbar.MaterialSearchBar
import dagger.hilt.android.AndroidEntryPoint
import hr.kfurjan.formula1.databinding.FragmentConstructorsBinding
import hr.kfurjan.formula1.model.enum.ConstructorSpinnerOptions
import hr.kfurjan.formula1.viewmodel.ConstructorViewModel

@AndroidEntryPoint
class ConstructorsFragment : Fragment() {

    private var _binding: FragmentConstructorsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: ConstructorViewModel by viewModels()
    private lateinit var constructorsAdapter: ConstructorsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConstructorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HostActivity).supportActionBar?.title = getString(R.string.constructors)

        initSpinner()
        initAdapter()
        initSearchListeners()
    }

    private fun initSpinner() =
        binding.constructorSpinner.setItems(
            resources.getStringArray(R.array.constructor_spinner_values).toMutableList()
        )

    private fun initAdapter() {
        constructorsAdapter = ConstructorsAdapter(requireContext())
        binding.rvConstructor.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = constructorsAdapter
        }

        viewModel.getConstructorsData().observe(
            viewLifecycleOwner,
            { constructors ->
                constructorsAdapter.setConstructors(constructors)
            }
        )
    }

    private fun initSearchListeners() {
        binding.constructorSearchBar.setOnSearchActionListener(object :
                MaterialSearchBar.OnSearchActionListener {
                override fun onSearchConfirmed(text: CharSequence?) {
                    when (binding.constructorSpinner.selectedIndex) {
                        ConstructorSpinnerOptions.NAME.ordinal -> viewModel.filterConstructorsByName(text.toString())
                        ConstructorSpinnerOptions.NATIONALITY.ordinal -> viewModel.filterConstructorsByNationality(text.toString())
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
