package hr.algebra.formula1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

    private lateinit var drivers: List<Driver>

    init {
        db = Formula1Database.getInstance(context)
        fetchDrivers()
    }

    fun getDrivers() = drivers

    private fun fetchDrivers() {
        runBlocking {
            val job = viewModelScope.async {
                db.driverDao().query()
            }
            drivers = job.await()
        }
    }

    fun filterDriversByName(firstName: String): List<Driver> {
        var drivers: List<Driver> = listOf()
        runBlocking {
            val job = viewModelScope.async {
                db.driverDao().queryByName(firstName)
            }
            drivers = job.await()
        }
        return drivers
    }

    fun filterDriversByLastName(lastName: String): List<Driver> {
        var drivers: List<Driver> = listOf()
        runBlocking {
            val job = viewModelScope.async {
                db.driverDao().queryByLastName(lastName)
            }
            drivers = job.await()
        }
        return drivers
    }

    fun filterDriversByNationality(nationality: String): List<Driver> {
        var drivers: List<Driver> = listOf()
        runBlocking {
            val job = viewModelScope.async {
                db.driverDao().queryByNationality(nationality)
            }
            drivers = job.await()
        }
        return drivers
    }
}
