package hr.algebra.formula1.dao.model

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.algebra.formula1.dao.Repository
import hr.algebra.formula1.model.Driver

@Dao
interface DriverDao : Repository<Driver> {

    @Insert
    override fun insert(data: Driver?): Long

    @Query("SELECT * FROM Driver")
    override fun query(): Cursor?

    @Query("SELECT * FROM Driver WHERE _id = :id")
    override fun queryById(id: Long): Cursor?

    @Query("DELETE FROM Driver")
    override fun delete(): Int

    @Query("DELETE FROM Driver WHERE _id = :id")
    override fun deleteById(id: Long): Int

    @Update
    override fun update(data: Driver?): Int
}
