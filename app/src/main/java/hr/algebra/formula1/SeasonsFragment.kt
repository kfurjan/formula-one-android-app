package hr.algebra.formula1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mancj.materialsearchbar.MaterialSearchBar
import hr.algebra.formula1.databinding.FragmentSeasonsBinding
import hr.algebra.formula1.viewmodel.SeasonViewModel

class SeasonsFragment : Fragment() {

    private var _binding: FragmentSeasonsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: SeasonViewModel by viewModels()
    private lateinit var seasonAdapter: SeasonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeasonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HostActivity).supportActionBar?.title = getString(R.string.seasons)

        initAdapter()
        initSearchListeners()
    }

    private fun initAdapter() {
        seasonAdapter = SeasonAdapter(requireContext())
        binding.rvSeasons.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = seasonAdapter
        }

        viewModel.getSeasonsData().observe(
            viewLifecycleOwner,
            { seasons ->
                seasonAdapter.setSeasons(seasons)
            }
        )
    }

    private fun initSearchListeners() {
        binding.seasonsSearchBar.setOnSearchActionListener(object :
                MaterialSearchBar.OnSearchActionListener {
                override fun onSearchConfirmed(text: CharSequence?) =
                    viewModel.filterSeasonsByYear(text.toString())

                override fun onSearchStateChanged(enabled: Boolean) {}
                override fun onButtonClicked(buttonCode: Int) {}
            })
    }
}
