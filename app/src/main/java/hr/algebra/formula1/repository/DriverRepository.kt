package hr.algebra.formula1.repository

import android.content.Context
import androidx.lifecycle.LiveData
import hr.algebra.formula1.dao.Formula1Database
import hr.algebra.formula1.dao.model.DriverDao
import hr.algebra.formula1.model.Driver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class DriverRepository(context: Context) {

    private val repositoryScope = CoroutineScope(Dispatchers.IO)
    private val driverDao: DriverDao = Formula1Database.getInstance(context).driverDao()

    suspend fun insertDriver(driver: Driver) = driverDao.insert(driver)

    fun getAllDrives(): LiveData<List<Driver>> {
        lateinit var drivers: LiveData<List<Driver>>
        runBlocking {
            val job = repositoryScope.async {
                driverDao.query()
            }
            drivers = job.await()
        }
        return drivers
    }

    fun getDriversFilteredByName(name: String): LiveData<List<Driver>> {
        lateinit var drivers: LiveData<List<Driver>>
        runBlocking {
            val job = repositoryScope.async {
                driverDao.queryByName(name)
            }
            drivers = job.await()
        }
        return drivers
    }

    fun getDriversFilteredByLastName(lastName: String): LiveData<List<Driver>> {
        lateinit var drivers: LiveData<List<Driver>>
        runBlocking {
            val job = repositoryScope.async {
                driverDao.queryByLastName(lastName)
            }
            drivers = job.await()
        }
        return drivers
    }

    fun getDriversFilteredByNationality(nationality: String): LiveData<List<Driver>> {
        lateinit var drivers: LiveData<List<Driver>>
        runBlocking {
            val job = repositoryScope.async {
                driverDao.queryByNationality(nationality)
            }
            drivers = job.await()
        }
        return drivers
    }
}
