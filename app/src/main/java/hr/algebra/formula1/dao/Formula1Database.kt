package hr.algebra.formula1.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.algebra.formula1.R
import hr.algebra.formula1.dao.model.CircuitDao
import hr.algebra.formula1.dao.model.ConstructorDao
import hr.algebra.formula1.dao.model.DriverDao
import hr.algebra.formula1.dao.model.SeasonDao
import hr.algebra.formula1.model.Circuit
import hr.algebra.formula1.model.Constructor
import hr.algebra.formula1.model.Driver
import hr.algebra.formula1.model.Season
import hr.algebra.formula1.util.SingletonHolder

@Database(
    entities = [Driver::class, Circuit::class, Constructor::class, Season::class],
    version = 1,
    exportSchema = true
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
