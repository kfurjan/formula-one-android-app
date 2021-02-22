package hr.kfurjan.formula1.dao.model

import androidx.room.Dao
import androidx.room.Query
import hr.kfurjan.formula1.dao.BaseDao
import hr.kfurjan.formula1.model.Constructor
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ConstructorDao : BaseDao<Constructor> {

    @Query("SELECT * FROM constructors_table")
    abstract fun query(): Flow<List<Constructor>>

    @Query("SELECT * FROM constructors_table WHERE name LIKE '%' || :name || '%'")
    abstract fun queryByName(name: String): Flow<List<Constructor>>

    @Query("SELECT * FROM constructors_table WHERE nationality LIKE '%' || :nationality || '%'")
    abstract fun queryByNationality(nationality: String): Flow<List<Constructor>>
}
