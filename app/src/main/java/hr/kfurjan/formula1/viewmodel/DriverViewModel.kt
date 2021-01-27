package hr.kfurjan.formula1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import hr.kfurjan.formula1.factory.RepositoryFactory
import hr.kfurjan.formula1.model.Driver
import hr.kfurjan.formula1.repository.DriverRepository

class DriverViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val repository: DriverRepository = RepositoryFactory.getRepository(context)

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
