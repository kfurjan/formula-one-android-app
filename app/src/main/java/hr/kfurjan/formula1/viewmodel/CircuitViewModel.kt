package hr.kfurjan.formula1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.kfurjan.formula1.model.Circuit
import hr.kfurjan.formula1.repository.CircuitRepository
import javax.inject.Inject

@HiltViewModel
class CircuitViewModel @Inject constructor(private val repository: CircuitRepository) :
    ViewModel() {

    private val _circuits = MediatorLiveData<List<Circuit>>()
    private val circuits: LiveData<List<Circuit>>
        get() = _circuits

    init {
        getAllCircuits()
    }

    fun getCircuitsData() = circuits

    private fun getAllCircuits() =
        _circuits.addSource(repository.queryAll().asLiveData()) { circuits ->
            _circuits.value = circuits
        }

    fun filterCircuitsByName(name: String) =
        _circuits.addSource(
            repository.getCircuitsFilteredByName(name).asLiveData()
        ) { circuits ->
            _circuits.value = circuits
        }

    fun filterCircuitsByCountry(country: String) =
        _circuits.addSource(
            repository.getCircuitsFilteredByCountry(country).asLiveData()
        ) { circuits ->
            _circuits.value = circuits
        }
}
