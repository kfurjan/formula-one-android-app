package hr.kfurjan.formula1.repository

import hr.kfurjan.formula1.dao.model.ConstructorDao
import hr.kfurjan.formula1.model.Constructor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConstructorRepository @Inject constructor(private val constructorDao: ConstructorDao) :
    Repository<Constructor> {

    override fun queryAll(): Flow<List<Constructor>> = constructorDao.query()

    override suspend fun insert(data: Constructor) = constructorDao.insert(data)

    override suspend fun update(data: Constructor) = constructorDao.update(data)

    override suspend fun delete(data: Constructor) = constructorDao.delete(data)

    fun getConstructorsFilteredByName(name: String): Flow<List<Constructor>> =
        constructorDao.queryByName(name)

    fun getConstructorsFilteredByNationality(nationality: String): Flow<List<Constructor>> =
        constructorDao.queryByNationality(nationality)
}
