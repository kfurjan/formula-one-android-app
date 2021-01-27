package hr.kfurjan.formula1.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import hr.kfurjan.formula1.dao.model.CircuitDao
import hr.kfurjan.formula1.model.Circuit
import javax.inject.Inject

class CircuitRepository @Inject constructor(private val circuitDao: CircuitDao) :
    Repository<Circuit> {

    override fun queryAll(): LiveData<List<Circuit>> =
        Transformations.map(circuitDao.query()) { circuits -> circuits }

    override suspend fun insert(data: Circuit) = circuitDao.insert(data)

    override suspend fun update(data: Circuit) = circuitDao.update(data)

    override suspend fun delete(data: Circuit) = circuitDao.delete(data)

    fun getCircuitsFilteredByName(name: String): LiveData<List<Circuit>> =
        Transformations.map(circuitDao.queryByName(name)) { circuits -> circuits }

    fun getCircuitsFilteredByCountry(country: String): LiveData<List<Circuit>> =
        Transformations.map(circuitDao.queryByCountry(country)) { circuits -> circuits }
}
