package hr.algebra.formula1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.formula1.dao.Formula1Database
import hr.algebra.formula1.databinding.FragmentDriversBinding
import hr.algebra.formula1.model.Driver
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class DriversFragment : Fragment() {

    private var _binding: FragmentDriversBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: Formula1Database
    private lateinit var drivers: MutableList<Driver>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        db = Formula1Database.getInstance(requireContext())
        runBlocking {
            val job = async {
                db.driverDao().query()
            }
            drivers = job.await()
        }

        _binding = FragmentDriversBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HostActivity).supportActionBar?.title = getString(R.string.drivers)
        binding.driverSearchBar.inflateMenu(R.menu.driver_search_menu)

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
