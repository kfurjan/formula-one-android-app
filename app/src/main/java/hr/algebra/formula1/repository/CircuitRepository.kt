package hr.algebra.formula1.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import hr.algebra.formula1.dao.Formula1Database
import hr.algebra.formula1.dao.model.CircuitDao
import hr.algebra.formula1.model.Circuit

class CircuitRepository(context: Context) : Repository<Circuit> {

    private val circuitDao: CircuitDao = Formula1Database.getInstance(context).circuitDao()

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
