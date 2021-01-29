package hr.kfurjan.formula1.dao.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import hr.kfurjan.formula1.dao.BaseDao
import hr.kfurjan.formula1.model.Driver

@Dao
abstract class DriverDao : BaseDao<Driver> {

    @Query("SELECT * FROM drivers_table")
    abstract fun query(): LiveData<List<Driver>>

    @Query("SELECT * FROM drivers_table WHERE firstName LIKE '%' || :firstName || '%'")
    abstract fun queryByName(firstName: String): LiveData<List<Driver>>

    @Query("SELECT * FROM drivers_table WHERE lastName LIKE '%' || :lastName || '%'")
    abstract fun queryByLastName(lastName: String): LiveData<List<Driver>>

    @Query("SELECT * FROM drivers_table WHERE nationality LIKE '%' || :nationality || '%'")
    abstract fun queryByNationality(nationality: String): LiveData<List<Driver>>
}
