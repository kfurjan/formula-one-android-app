package hr.kfurjan.formula1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Circuit(
    @PrimaryKey(autoGenerate = true) val _id: Long?,
    val circuitId: String,
    val name: String,
    val latitude: String,
    val longitude: String,
    val localName: String,
    val countryName: String,
    val url: String
)
