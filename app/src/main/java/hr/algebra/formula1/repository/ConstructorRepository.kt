package hr.algebra.formula1.repository

import android.content.Context
import hr.algebra.formula1.dao.Formula1Database
import hr.algebra.formula1.dao.model.ConstructorDao
import hr.algebra.formula1.model.Constructor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ConstructorRepository(context: Context) {

    private val repositoryScope = CoroutineScope(Dispatchers.IO)
    private val constructorDao: ConstructorDao =
        Formula1Database.getInstance(context).constructorDao()

    suspend fun insertConstructor(constructor: Constructor) = constructorDao.insert(constructor)
}
