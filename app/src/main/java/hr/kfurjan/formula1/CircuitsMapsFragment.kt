package hr.kfurjan.formula1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import hr.kfurjan.formula1.databinding.FragmentCircuitsMapsBinding
import hr.kfurjan.formula1.viewmodel.CircuitViewModel

@AndroidEntryPoint
class CircuitsMapsFragment : Fragment() {

    private var _binding: FragmentCircuitsMapsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: CircuitViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCircuitsMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        addMarkersToMap()
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
}
