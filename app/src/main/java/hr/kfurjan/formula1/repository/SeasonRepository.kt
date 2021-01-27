package hr.kfurjan.formula1.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import hr.kfurjan.formula1.dao.model.SeasonDao
import hr.kfurjan.formula1.model.Season
import javax.inject.Inject

class SeasonRepository @Inject constructor(private val seasonDao: SeasonDao) : Repository<Season> {

    override fun queryAll(): LiveData<List<Season>> =
        Transformations.map(seasonDao.query()) { seasons -> seasons }

    override suspend fun insert(data: Season) = seasonDao.insert(data)

    override suspend fun update(data: Season) = seasonDao.update(data)

    override suspend fun delete(data: Season) = seasonDao.delete(data)

    fun getSeasonsFilteredByYear(year: String): LiveData<List<Season>> =
        Transformations.map(seasonDao.queryByYear(year)) { seasons -> seasons }
}
