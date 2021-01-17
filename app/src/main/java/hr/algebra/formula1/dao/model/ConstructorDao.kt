package hr.algebra.formula1.dao.model

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.algebra.formula1.dao.Repository
import hr.algebra.formula1.model.Constructor

@Dao
interface ConstructorDao : Repository<Constructor> {

    @Insert
    override fun insert(data: Constructor?): Long

    @Query("SELECT * FROM Constructor")
    override fun query(): Cursor?

    @Query("SELECT * FROM Constructor WHERE _id = :id")
    override fun queryById(id: Long): Cursor?

    @Query("DELETE FROM Constructor")
    override fun delete(): Int

    @Query("DELETE FROM Constructor WHERE _id = :id")
    override fun deleteById(id: Long): Int

    @Update
    override fun update(data: Constructor?): Int
}
