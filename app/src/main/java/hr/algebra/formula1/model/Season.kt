package hr.algebra.formula1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Season(
    @PrimaryKey(autoGenerate = true) val _id: Long?,
    val year: String,
    val url: String
)
