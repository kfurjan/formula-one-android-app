package hr.kfurjan.formula1.repository

import hr.kfurjan.formula1.dao.model.SeasonDao
import hr.kfurjan.formula1.model.Season
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeasonRepository @Inject constructor(private val seasonDao: SeasonDao) : Repository<Season> {

    override fun queryAll(): Flow<List<Season>> = seasonDao.query()

    override suspend fun insert(data: Season) = seasonDao.insert(data)

    override suspend fun update(data: Season) = seasonDao.update(data)

    override suspend fun delete(data: Season) = seasonDao.delete(data)

    fun getSeasonsFilteredByYear(year: String): Flow<List<Season>> = seasonDao.queryByYear(year)
}
