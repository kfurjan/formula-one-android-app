package hr.kfurjan.formula1.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import hr.kfurjan.formula1.dao.model.DriverDao
import hr.kfurjan.formula1.model.Driver
import javax.inject.Inject

class DriverRepository @Inject constructor(private val driverDao: DriverDao) : Repository<Driver> {

    override fun queryAll(): LiveData<List<Driver>> =
        Transformations.map(driverDao.query()) { drivers -> drivers }

    override suspend fun insert(data: Driver) = driverDao.insert(data)

    override suspend fun update(data: Driver) = driverDao.update(data)

    override suspend fun delete(data: Driver) = driverDao.delete(data)

    fun getDriversFilteredByName(name: String): LiveData<List<Driver>> =
        Transformations.map(driverDao.queryByName(name)) { drivers -> drivers }

    fun getDriversFilteredByLastName(lastName: String): LiveData<List<Driver>> =
        Transformations.map(driverDao.queryByLastName(lastName)) { drivers -> drivers }

    fun getDriversFilteredByNationality(nationality: String): LiveData<List<Driver>> =
        Transformations.map(driverDao.queryByNationality(nationality)) { drivers -> drivers }
}
