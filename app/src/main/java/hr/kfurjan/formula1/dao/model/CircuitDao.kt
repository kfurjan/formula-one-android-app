package hr.kfurjan.formula1.dao.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import hr.kfurjan.formula1.dao.BaseDao
import hr.kfurjan.formula1.model.Circuit

@Dao
abstract class CircuitDao : BaseDao<Circuit> {

    @Query("SELECT * FROM circuits_table")
    abstract fun query(): LiveData<List<Circuit>>

    @Query("SELECT * FROM circuits_table WHERE name LIKE '%' || :name || '%'")
    abstract fun queryByName(name: String): LiveData<List<Circuit>>

    @Query("SELECT * FROM circuits_table WHERE countryName LIKE '%' || :country || '%'")
    abstract fun queryByCountry(country: String): LiveData<List<Circuit>>
}
