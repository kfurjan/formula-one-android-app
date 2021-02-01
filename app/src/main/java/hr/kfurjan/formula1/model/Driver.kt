package hr.kfurjan.formula1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drivers_table")
data class Driver(
    @PrimaryKey(autoGenerate = true) val _id: Long?,
    @ColumnInfo(name = "driverId") val driverId: String,
    @ColumnInfo(name = "firstName") val firstName: String,
    @ColumnInfo(name = "lastName") val lastName: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "birthDate") val birthDate: String,
    @ColumnInfo(name = "nationality") val nationality: String,
    @ColumnInfo(name = "code") val code: String?,
    @ColumnInfo(name = "number") val number: String?
)
