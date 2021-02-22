package hr.kfurjan.formula1.repository

import hr.kfurjan.formula1.dao.model.DriverDao
import hr.kfurjan.formula1.model.Driver
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DriverRepository @Inject constructor(private val driverDao: DriverDao) : Repository<Driver> {

    override fun queryAll(): Flow<List<Driver>> = driverDao.query()

    override suspend fun insert(data: Driver) = driverDao.insert(data)

    override suspend fun update(data: Driver) = driverDao.update(data)

    override suspend fun delete(data: Driver) = driverDao.delete(data)

    fun getDriversFilteredByName(name: String): Flow<List<Driver>> = driverDao.queryByName(name)

    fun getDriversFilteredByLastName(lastName: String): Flow<List<Driver>> =
        driverDao.queryByLastName(lastName)

    fun getDriversFilteredByNationality(nationality: String): Flow<List<Driver>> =
        driverDao.queryByNationality(nationality)
}
