package hr.algebra.formula1.dao.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.algebra.formula1.dao.Repository
import hr.algebra.formula1.model.Season

@Dao
interface SeasonDao : Repository<Season> {

    @Insert
    override suspend fun insert(data: Season): Long

    @Query("SELECT * FROM Season")
    override suspend fun query(): MutableList<Season>

    @Query("SELECT * FROM Season WHERE _id = :id")
    override suspend fun queryById(id: Long): Season

    @Query("DELETE FROM Season")
    override suspend fun delete(): Int

    @Query("DELETE FROM Season WHERE _id = :id")
    override suspend fun deleteById(id: Long): Int

    @Update
    override suspend fun update(data: Season): Int
}
