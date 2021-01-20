package hr.algebra.formula1.dao.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.algebra.formula1.model.Season

@Dao
interface SeasonDao {

    @Insert
    suspend fun insert(data: Season): Long

    @Query("SELECT * FROM Season")
    suspend fun query(): MutableList<Season>

    @Query("SELECT * FROM Season WHERE _id = :id")
    suspend fun queryById(id: Long): Season

    @Query("DELETE FROM Season")
    suspend fun delete(): Int

    @Query("DELETE FROM Season WHERE _id = :id")
    suspend fun deleteById(id: Long): Int

    @Update
    suspend fun update(data: Season): Int
}
