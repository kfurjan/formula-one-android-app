package hr.kfurjan.formula1.dao.model

import androidx.room.Dao
import androidx.room.Query
import hr.kfurjan.formula1.dao.BaseDao
import hr.kfurjan.formula1.model.Circuit
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CircuitDao : BaseDao<Circuit> {

    @Query("SELECT * FROM circuits_table")
    abstract fun query(): Flow<List<Circuit>>

    @Query("SELECT * FROM circuits_table WHERE name LIKE '%' || :name || '%'")
    abstract fun queryByName(name: String): Flow<List<Circuit>>

    @Query("SELECT * FROM circuits_table WHERE countryName LIKE '%' || :country || '%'")
    abstract fun queryByCountry(country: String): Flow<List<Circuit>>
}
