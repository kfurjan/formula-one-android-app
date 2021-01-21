package hr.algebra.formula1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import hr.algebra.formula1.dao.Formula1Database
import hr.algebra.formula1.model.Driver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class DriverViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val viewModelScope = CoroutineScope(Dispatchers.IO)
    private val db: Formula1Database

    private val mDrivers = MediatorLiveData<List<Driver>>()

    init {
        db = Formula1Database.getInstance(context)
        fetchDrivers()
    }

    fun getDrivers() = mDrivers

    private fun fetchDrivers() {
        runBlocking {
            val job = viewModelScope.async {
                db.driverDao().query()
            }
            val driversLiveData = job.await()
            mDrivers.addSource(driversLiveData) { drivers -> mDrivers.value = drivers }
        }
    }

    fun filterDriversByName(firstName: String) {
        runBlocking {
            val job = viewModelScope.async {
                db.driverDao().queryByName(firstName)
            }
            val driversLiveData = job.await()
            mDrivers.addSource(driversLiveData) { drivers -> mDrivers.value = drivers }
        }
    }

    fun filterDriversByLastName(lastName: String) {
        runBlocking {
            val job = viewModelScope.async {
                db.driverDao().queryByLastName(lastName)
            }
            val driversLiveData = job.await()
            mDrivers.addSource(driversLiveData) { drivers -> mDrivers.value = drivers }
        }
    }

    fun filterDriversByNationality(nationality: String) {
        runBlocking {
            val job = viewModelScope.async {
                db.driverDao().queryByNationality(nationality)
            }
            val driversLiveData = job.await()
            mDrivers.addSource(driversLiveData) { drivers -> mDrivers.value = drivers }
        }
    }
}
