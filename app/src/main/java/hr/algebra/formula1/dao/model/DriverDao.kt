package hr.algebra.formula1.dao.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.algebra.formula1.dao.Repository
import hr.algebra.formula1.model.Driver

@Dao
interface DriverDao : Repository<Driver> {

    @Insert
    override suspend fun insert(data: Driver): Long

    @Query("SELECT * FROM Driver")
    override suspend fun query(): MutableList<Driver>

    @Query("SELECT * FROM Driver WHERE _id = :id")
    override suspend fun queryById(id: Long): Driver

    @Query("DELETE FROM Driver")
    override suspend fun delete(): Int

    @Query("DELETE FROM Driver WHERE _id = :id")
    override suspend fun deleteById(id: Long): Int

    @Update
    override suspend fun update(data: Driver): Int
}
