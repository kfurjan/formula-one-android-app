package hr.algebra.formula1.repository

import android.content.Context
import hr.algebra.formula1.dao.Formula1Database
import hr.algebra.formula1.dao.model.CircuitDao
import hr.algebra.formula1.model.Circuit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class CircuitRepository(context: Context) {

    private val repositoryScope = CoroutineScope(Dispatchers.IO)
    private val circuitDao: CircuitDao =
        Formula1Database.getInstance(context).circuitDao()

    suspend fun insertCircuit(circuit: Circuit) = circuitDao.insert(circuit)
}
