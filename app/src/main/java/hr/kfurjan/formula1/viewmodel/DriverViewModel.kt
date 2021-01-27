package hr.kfurjan.formula1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.kfurjan.formula1.model.Driver
import hr.kfurjan.formula1.repository.DriverRepository
import javax.inject.Inject

@HiltViewModel
class DriverViewModel @Inject constructor(private val repository: DriverRepository) : ViewModel() {

    private val _drivers = MediatorLiveData<List<Driver>>()
    private val drivers: LiveData<List<Driver>>
        get() = _drivers

    init {
        getAllDrivers()
    }

    fun getDriversData() = drivers

    private fun getAllDrivers() =
        _drivers.addSource(repository.queryAll()) { drivers ->
            _drivers.value = drivers
        }

    fun filterDriversByName(firstName: String) =
        _drivers.addSource(repository.getDriversFilteredByName(firstName)) { drivers ->
            _drivers.value = drivers
        }

    fun filterDriversByLastName(lastName: String) =
        _drivers.addSource(repository.getDriversFilteredByLastName(lastName)) { drivers ->
            _drivers.value = drivers
        }

    fun filterDriversByNationality(nationality: String) =
        _drivers.addSource(repository.getDriversFilteredByNationality(nationality)) { drivers ->
            _drivers.value = drivers
        }
}
