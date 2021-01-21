package hr.algebra.formula1.dao.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.algebra.formula1.model.Driver

@Dao
interface DriverDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: Driver): Long

    @Query("SELECT * FROM Driver")
    fun query(): LiveData<List<Driver>>

    @Query("SELECT * FROM Driver WHERE firstName LIKE '%' || :firstName || '%'")
    fun queryByName(firstName: String): LiveData<List<Driver>>

    @Query("SELECT * FROM Driver WHERE lastName LIKE '%' || :lastName || '%'")
    fun queryByLastName(lastName: String): LiveData<List<Driver>>

    @Query("SELECT * FROM Driver WHERE nationality LIKE '%' || :nationality || '%'")
    fun queryByNationality(nationality: String): LiveData<List<Driver>>
}
