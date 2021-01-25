package hr.algebra.formula1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import hr.algebra.formula1.databinding.FragmentCircuitsMapsBinding
import hr.algebra.formula1.viewmodel.CircuitViewModel

class CircuitsMapsFragment : Fragment() {

    private var _binding: FragmentCircuitsMapsBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: CircuitViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCircuitsMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
        // addMarkersToMap()
    }

    private fun initViewModel() {
        viewModel = CircuitViewModel.getInstance((activity as HostActivity).application)
    }

    private fun addMarkersToMap() {
        (childFragmentManager.findFragmentById(R.id.googleMaps) as SupportMapFragment).getMapAsync {
            viewModel.getCircuitsData().observe(
                viewLifecycleOwner,
                { circuits ->
                    circuits.forEach { circuit ->
                        it.addMarker(
                            MarkerOptions().position(
                                LatLng(circuit.latitude.toDouble(), circuit.longitude.toDouble())
                            )
                                .title(circuit.name)
                                .snippet(circuit.countryName)
                        )
                    }
                }
            )
        }
    }

    override fun onResume() {
        super.onResume()
        addMarkersToMap()
    }
}
