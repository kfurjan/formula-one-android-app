package hr.algebra.formula1.dao.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.algebra.formula1.model.Circuit

@Dao
interface CircuitDao {

    @Insert
    suspend fun insert(data: Circuit): Long

    @Query("SELECT * FROM Circuit")
    suspend fun query(): MutableList<Circuit>

    @Query("SELECT * FROM Circuit WHERE _id = :id")
    suspend fun queryById(id: Long): Circuit

    @Query("DELETE FROM Circuit")
    suspend fun delete(): Int

    @Query("DELETE FROM Circuit WHERE _id = :id")
    suspend fun deleteById(id: Long): Int

    @Update
    suspend fun update(data: Circuit): Int
}
