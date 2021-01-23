package hr.algebra.formula1.dao.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import hr.algebra.formula1.dao.BaseDao
import hr.algebra.formula1.model.Circuit

@Dao
abstract class CircuitDao : BaseDao<Circuit> {

    @Query("SELECT * FROM Circuit")
    abstract fun query(): LiveData<List<Circuit>>
}
