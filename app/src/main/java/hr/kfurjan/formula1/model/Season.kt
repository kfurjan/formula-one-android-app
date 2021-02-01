package hr.kfurjan.formula1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seasons_table")
data class Season(
    @PrimaryKey(autoGenerate = true) val _id: Long?,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "url") val url: String
)
