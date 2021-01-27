package hr.kfurjan.formula1.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import hr.kfurjan.formula1.dao.model.CircuitDao
import hr.kfurjan.formula1.dao.model.ConstructorDao
import hr.kfurjan.formula1.dao.model.DriverDao
import hr.kfurjan.formula1.dao.model.SeasonDao
import hr.kfurjan.formula1.model.Circuit
import hr.kfurjan.formula1.model.Constructor
import hr.kfurjan.formula1.model.Driver
import hr.kfurjan.formula1.model.Season

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
}
