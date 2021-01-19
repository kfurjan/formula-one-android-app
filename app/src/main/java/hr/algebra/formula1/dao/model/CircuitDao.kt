package hr.algebra.formula1.dao.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.algebra.formula1.dao.Repository
import hr.algebra.formula1.model.Circuit

@Dao
interface CircuitDao : Repository<Circuit> {

    @Insert
    override suspend fun insert(data: Circuit): Long

    @Query("SELECT * FROM Circuit")
    override suspend fun query(): MutableList<Circuit>

    @Query("SELECT * FROM Circuit WHERE _id = :id")
    override suspend fun queryById(id: Long): Circuit

    @Query("DELETE FROM Circuit")
    override suspend fun delete(): Int

    @Query("DELETE FROM Circuit WHERE _id = :id")
    override suspend fun deleteById(id: Long): Int

    @Update
    override suspend fun update(data: Circuit): Int
}
