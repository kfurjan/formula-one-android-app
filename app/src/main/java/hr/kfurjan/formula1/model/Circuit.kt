package hr.kfurjan.formula1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "circuits_table")
data class Circuit(
    @PrimaryKey(autoGenerate = true) val _id: Long?,
    @ColumnInfo(name = "circuitId") val circuitId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "latitude") val latitude: String,
    @ColumnInfo(name = "longitude") val longitude: String,
    @ColumnInfo(name = "localName") val localName: String,
    @ColumnInfo(name = "countryName") val countryName: String,
    @ColumnInfo(name = "url") val url: String
)
