package hr.kfurjan.formula1.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import hr.kfurjan.formula1.dao.Formula1Database
import hr.kfurjan.formula1.dao.model.ConstructorDao
import hr.kfurjan.formula1.model.Constructor

class ConstructorRepository(context: Context) : Repository<Constructor> {

    private val constructorDao: ConstructorDao =
        Formula1Database.getInstance(context).constructorDao()

    override fun queryAll(): LiveData<List<Constructor>> =
        Transformations.map(constructorDao.query()) { constructors -> constructors }

    override suspend fun insert(data: Constructor) = constructorDao.insert(data)

    override suspend fun update(data: Constructor) = constructorDao.update(data)

    override suspend fun delete(data: Constructor) = constructorDao.delete(data)

    fun getConstructorsFilteredByName(name: String): LiveData<List<Constructor>> =
        Transformations.map(constructorDao.queryByName(name)) { constructors -> constructors }

    fun getConstructorsFilteredByNationality(nationality: String): LiveData<List<Constructor>> =
        Transformations.map(constructorDao.queryByNationality(nationality)) { constructors -> constructors }
}
