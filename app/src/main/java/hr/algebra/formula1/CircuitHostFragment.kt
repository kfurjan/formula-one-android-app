package hr.algebra.formula1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import hr.algebra.formula1.databinding.FragmentCircuitHostBinding

class CircuitHostFragment : Fragment() {

    private var _binding: FragmentCircuitHostBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var tabsAdapter: TabbedCollectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCircuitHostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HostActivity).supportActionBar?.title = getString(R.string.circuits)

        initAdapter()
        initViewPager()
        initTabs()
    }

    private fun initAdapter() {
        tabsAdapter = TabbedCollectionAdapter(childFragmentManager, lifecycle)

        tabsAdapter.addFragment(Pair(CircuitsFragment(), getString(R.string.circuits_tab_title)))
        tabsAdapter.addFragment(Pair(CircuitsMapsFragment(), getString(R.string.maps_tab_title)))
    }

    private fun initViewPager() {
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPager.adapter = tabsAdapter
    }

    private fun initTabs() =
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabsAdapter.getTabTitle(position)
        }.attach()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
