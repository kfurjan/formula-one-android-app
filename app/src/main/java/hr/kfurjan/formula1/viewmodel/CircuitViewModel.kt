package hr.kfurjan.formula1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import hr.kfurjan.formula1.factory.RepositoryFactory
import hr.kfurjan.formula1.model.Circuit
import hr.kfurjan.formula1.repository.CircuitRepository
import hr.kfurjan.formula1.util.SingletonHolder

class CircuitViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val repository: CircuitRepository = RepositoryFactory.getRepository(context)

    private val _circuits = MediatorLiveData<List<Circuit>>()
    private val circuits: LiveData<List<Circuit>>
        get() = _circuits

    companion object : SingletonHolder<CircuitViewModel, Application>({ CircuitViewModel(it) })

    init {
        getAllCircuits()
    }

    fun getCircuitsData() = circuits

    private fun getAllCircuits() =
        _circuits.addSource(repository.queryAll()) { circuits ->
            _circuits.value = circuits
        }

    fun filterCircuitsByName(name: String) =
        _circuits.addSource(repository.getCircuitsFilteredByName(name)) { circuits ->
            _circuits.value = circuits
        }

    fun filterCircuitsByCountry(country: String) =
        _circuits.addSource(repository.getCircuitsFilteredByCountry(country)) { circuits ->
            _circuits.value = circuits
        }
}
