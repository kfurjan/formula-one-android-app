package hr.kfurjan.formula1.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import hr.kfurjan.formula1.dao.model.ConstructorDao
import hr.kfurjan.formula1.model.Constructor
import javax.inject.Inject

class ConstructorRepository @Inject constructor(private val constructorDao: ConstructorDao) :
    Repository<Constructor> {

    override fun queryAll(): LiveData<List<Constructor>> =
        Transformations.map(constructorDao.query()) { constructors -> constructors }

    override suspend fun insert(data: Constructor) = constructorDao.insert(data)

    override suspend fun update(data: Constructor) = constructorDao.update(data)

    override suspend fun delete(data: Constructor) = constructorDao.delete(data)

    fun getConstructorsFilteredByName(name: String): LiveData<List<Constructor>> =
        Transformations
            .map(constructorDao.queryByName(name)) { constructors -> constructors }

    fun getConstructorsFilteredByNationality(nationality: String): LiveData<List<Constructor>> =
        Transformations
            .map(constructorDao.queryByNationality(nationality)) { constructors -> constructors }
}
