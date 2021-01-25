package hr.algebra.formula1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Constructor(
    @PrimaryKey(autoGenerate = true) val _id: Long?,
    val constructorId: String,
    val name: String,
    val nationality: String,
    val url: String
)
