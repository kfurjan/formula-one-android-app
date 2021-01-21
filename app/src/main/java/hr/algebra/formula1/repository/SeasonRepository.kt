package hr.algebra.formula1.repository

import android.content.Context
import hr.algebra.formula1.dao.Formula1Database
import hr.algebra.formula1.dao.model.SeasonDao
import hr.algebra.formula1.model.Season
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class SeasonRepository(context: Context) {

    private val repositoryScope = CoroutineScope(Dispatchers.IO)
    private val seasonDao: SeasonDao =
        Formula1Database.getInstance(context).seasonDao()

    suspend fun insertSeason(season: Season) = seasonDao.insert(season)
}
