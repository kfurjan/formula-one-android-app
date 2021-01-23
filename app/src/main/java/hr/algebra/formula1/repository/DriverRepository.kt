package hr.algebra.formula1.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import hr.algebra.formula1.dao.Formula1Database
import hr.algebra.formula1.dao.model.DriverDao
import hr.algebra.formula1.model.Driver

class DriverRepository(context: Context) : Repository<Driver> {

    private val driverDao: DriverDao = Formula1Database.getInstance(context).driverDao()

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
