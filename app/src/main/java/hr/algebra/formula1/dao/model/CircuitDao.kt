package hr.algebra.formula1.dao.model

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.algebra.formula1.dao.Repository
import hr.algebra.formula1.model.Circuit

@Dao
interface CircuitDao : Repository<Circuit> {

    @Insert
    override fun insert(data: Circuit?): Long

    @Query("SELECT * FROM Circuit")
    override fun query(): Cursor?

    @Query("SELECT * FROM Circuit WHERE _id = :id")
    override fun queryById(id: Long): Cursor?

    @Query("DELETE FROM Circuit")
    override fun delete(): Int

    @Query("DELETE FROM Circuit WHERE _id = :id")
    override fun deleteById(id: Long): Int

    @Update
    override fun update(data: Circuit?): Int
}
