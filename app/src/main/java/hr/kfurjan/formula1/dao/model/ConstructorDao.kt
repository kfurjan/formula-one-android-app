package hr.kfurjan.formula1.dao.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import hr.kfurjan.formula1.dao.BaseDao
import hr.kfurjan.formula1.model.Constructor

@Dao
abstract class ConstructorDao : BaseDao<Constructor> {

    @Query("SELECT * FROM constructors_table")
    abstract fun query(): LiveData<List<Constructor>>

    @Query("SELECT * FROM constructors_table WHERE name LIKE '%' || :name || '%'")
    abstract fun queryByName(name: String): LiveData<List<Constructor>>

    @Query("SELECT * FROM constructors_table WHERE nationality LIKE '%' || :nationality || '%'")
    abstract fun queryByNationality(nationality: String): LiveData<List<Constructor>>
}