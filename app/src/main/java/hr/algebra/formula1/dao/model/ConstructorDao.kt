package hr.algebra.formula1.dao.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.algebra.formula1.model.Constructor

@Dao
interface ConstructorDao {

    @Insert
    suspend fun insert(constructor: Constructor): Long

    @Query("SELECT * FROM Constructor")
    suspend fun query(): MutableList<Constructor>

    @Query("SELECT * FROM Constructor WHERE _id = :id")
    suspend fun queryById(id: Long): Constructor

    @Query("DELETE FROM Constructor")
    suspend fun delete(): Int

    @Query("DELETE FROM Constructor WHERE _id = :id")
    suspend fun deleteById(id: Long): Int

    @Update
    suspend fun update(data: Constructor): Int
}
