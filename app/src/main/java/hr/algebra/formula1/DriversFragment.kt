package hr.algebra.formula1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.formula1.databinding.FragmentDriversBinding
import hr.algebra.formula1.model.Driver

class DriversFragment : Fragment() {

    private var _binding: FragmentDriversBinding? = null
    private val binding get() = _binding!!

    private lateinit var drivers: MutableList<Driver>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDriversBinding.inflate(inflater, container, false)
        val tempDrivers: MutableList<Driver> = mutableListOf(
            Driver(1, "rosberg", "Nico", "Rosberg", "", "10-10-1980", "German", "ROS", "6"),
            Driver(1, "rosberg", "Lewis", "Hamilton", "", "10-10-1980", "British", "HAM", "6")
        )
        drivers = tempDrivers

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HostActivity).supportActionBar?.title = getString(R.string.drivers)
        val driverAdapter = DriverAdapter(drivers)
        binding.rvDrivers.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = driverAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
