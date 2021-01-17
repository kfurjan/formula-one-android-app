package hr.algebra.formula1.dao.model

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.algebra.formula1.dao.Repository
import hr.algebra.formula1.model.Season

@Dao
interface SeasonDao : Repository<Season> {

    @Insert
    override fun insert(data: Season?): Long

    @Query("SELECT * FROM Season")
    override fun query(): Cursor?

    @Query("SELECT * FROM Season WHERE _id = :id")
    override fun queryById(id: Long): Cursor?

    @Query("DELETE FROM Season")
    override fun delete(): Int

    @Query("DELETE FROM Season WHERE _id = :id")
    override fun deleteById(id: Long): Int

    @Update
    override fun update(data: Season?): Int
}