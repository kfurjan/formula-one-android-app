package hr.kfurjan.formula1.dao.model

import androidx.room.Dao
import androidx.room.Query
import hr.kfurjan.formula1.dao.BaseDao
import hr.kfurjan.formula1.model.Season
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SeasonDao : BaseDao<Season> {

    @Query("SELECT * FROM seasons_table")
    abstract fun query(): Flow<List<Season>>

    @Query("SELECT * FROM seasons_table WHERE year LIKE '%' || :year || '%'")
    abstract fun queryByYear(year: String): Flow<List<Season>>
}
