package hr.kfurjan.formula1.repository

import hr.kfurjan.formula1.dao.model.CircuitDao
import hr.kfurjan.formula1.model.Circuit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CircuitRepository @Inject constructor(private val circuitDao: CircuitDao) :
    Repository<Circuit> {

    override fun queryAll(): Flow<List<Circuit>> = circuitDao.query()

    override suspend fun insert(data: Circuit) = circuitDao.insert(data)

    override suspend fun update(data: Circuit) = circuitDao.update(data)

    override suspend fun delete(data: Circuit) = circuitDao.delete(data)

    fun getCircuitsFilteredByName(name: String): Flow<List<Circuit>> =
        circuitDao.queryByName(name)

    fun getCircuitsFilteredByCountry(country: String): Flow<List<Circuit>> =
        circuitDao.queryByCountry(country)
}
