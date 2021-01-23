package hr.algebra.formula1.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import hr.algebra.formula1.dao.Formula1Database
import hr.algebra.formula1.dao.model.SeasonDao
import hr.algebra.formula1.model.Season

class SeasonRepository(context: Context) : Repository<Season> {

    private val seasonDao: SeasonDao = Formula1Database.getInstance(context).seasonDao()

    override fun queryAll(): LiveData<List<Season>> =
        Transformations.map(seasonDao.query()) { seasons -> seasons }

    override suspend fun insert(data: Season) = seasonDao.insert(data)

    override suspend fun update(data: Season) = seasonDao.update(data)

    override suspend fun delete(data: Season) = seasonDao.delete(data)
}
