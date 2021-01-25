package hr.algebra.formula1.dao.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import hr.algebra.formula1.dao.BaseDao
import hr.algebra.formula1.model.Season

@Dao
abstract class SeasonDao : BaseDao<Season> {

    @Query("SELECT * FROM Season")
    abstract fun query(): LiveData<List<Season>>

    @Query("SELECT * FROM Season WHERE year LIKE '%' || :year || '%'")
    abstract fun queryByYear(year: String): LiveData<List<Season>>
}
