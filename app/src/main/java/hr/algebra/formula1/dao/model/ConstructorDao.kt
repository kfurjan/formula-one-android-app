package hr.algebra.formula1.dao.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.algebra.formula1.dao.Repository
import hr.algebra.formula1.model.Constructor

@Dao
interface ConstructorDao : Repository<Constructor> {

    @Insert
    override suspend fun insert(data: Constructor): Long

    @Query("SELECT * FROM Constructor")
    override suspend fun query(): MutableList<Constructor>

    @Query("SELECT * FROM Constructor WHERE _id = :id")
    override suspend fun queryById(id: Long): Constructor

    @Query("DELETE FROM Constructor")
    override suspend fun delete(): Int

    @Query("DELETE FROM Constructor WHERE _id = :id")
    override suspend fun deleteById(id: Long): Int

    @Update
    override suspend fun update(data: Constructor): Int
}
