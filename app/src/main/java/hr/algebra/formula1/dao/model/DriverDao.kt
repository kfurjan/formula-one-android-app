package hr.algebra.formula1.dao.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.algebra.formula1.model.Driver

@Dao
interface DriverDao {

    @Insert
    suspend fun insert(data: Driver): Long

    @Query("SELECT * FROM Driver")
    suspend fun query(): List<Driver>

    @Query("SELECT * FROM Driver WHERE firstName LIKE :firstName")
    suspend fun queryByName(firstName: String): List<Driver>

    @Query("SELECT * FROM Driver WHERE lastName LIKE :lastName")
    suspend fun queryByLastName(lastName: String): List<Driver>

    @Query("SELECT * FROM Driver WHERE nationality LIKE :nationality")
    suspend fun queryByNationality(nationality: String): List<Driver>

    @Query("SELECT * FROM Driver WHERE _id = :id")
    suspend fun queryById(id: Long): Driver

    @Query("DELETE FROM Driver")
    suspend fun delete(): Int

    @Query("DELETE FROM Driver WHERE _id = :id")
    suspend fun deleteById(id: Long): Int

    @Update
    suspend fun update(data: Driver): Int
}
