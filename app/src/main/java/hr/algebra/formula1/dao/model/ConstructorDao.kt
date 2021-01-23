package hr.algebra.formula1.dao.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import hr.algebra.formula1.dao.BaseDao
import hr.algebra.formula1.model.Constructor

@Dao
abstract class ConstructorDao : BaseDao<Constructor> {

    @Query("SELECT * FROM Constructor")
    abstract fun query(): LiveData<List<Constructor>>
}
