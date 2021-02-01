package hr.kfurjan.formula1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "constructors_table")
data class Constructor(
    @PrimaryKey(autoGenerate = true) val _id: Long?,
    @ColumnInfo(name = "constructorId") val constructorId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "nationality") val nationality: String,
    @ColumnInfo(name = "url") val url: String
)
