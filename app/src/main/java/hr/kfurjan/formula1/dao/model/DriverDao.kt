package hr.kfurjan.formula1.dao.model

import androidx.room.Dao
import androidx.room.Query
import hr.kfurjan.formula1.dao.BaseDao
import hr.kfurjan.formula1.model.Driver
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DriverDao : BaseDao<Driver> {

    @Query("SELECT * FROM drivers_table")
    abstract fun query(): Flow<List<Driver>>

    @Query("SELECT * FROM drivers_table WHERE firstName LIKE '%' || :firstName || '%'")
    abstract fun queryByName(firstName: String): Flow<List<Driver>>

    @Query("SELECT * FROM drivers_table WHERE lastName LIKE '%' || :lastName || '%'")
    abstract fun queryByLastName(lastName: String): Flow<List<Driver>>

    @Query("SELECT * FROM drivers_table WHERE nationality LIKE '%' || :nationality || '%'")
    abstract fun queryByNationality(nationality: String): Flow<List<Driver>>
}
