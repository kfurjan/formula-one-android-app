package hr.kfurjan.formula1.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.kfurjan.formula1.R
import hr.kfurjan.formula1.dao.model.CircuitDao
import hr.kfurjan.formula1.dao.model.ConstructorDao
import hr.kfurjan.formula1.dao.model.DriverDao
import hr.kfurjan.formula1.dao.model.SeasonDao
import hr.kfurjan.formula1.model.Circuit
import hr.kfurjan.formula1.model.Constructor
import hr.kfurjan.formula1.model.Driver
import hr.kfurjan.formula1.model.Season
import hr.kfurjan.formula1.util.SingletonHolder

@Database(
    entities = [Driver::class, Circuit::class, Constructor::class, Season::class],
    version = 1,
    exportSchema = false
)
abstract class Formula1Database : RoomDatabase() {
    abstract fun driverDao(): DriverDao
    abstract fun circuitDao(): CircuitDao
    abstract fun constructorDao(): ConstructorDao
    abstract fun seasonDao(): SeasonDao

    companion object : SingletonHolder<Formula1Database, Context>({
        Room.databaseBuilder(
            it.applicationContext,
            Formula1Database::class.java,
            it.getString(R.string.formula1_db)
        ).build()
    })
}
