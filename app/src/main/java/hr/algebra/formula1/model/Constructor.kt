package hr.algebra.formula1.model

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Constructor(
    @PrimaryKey(autoGenerate = true) val _id: Long?,
    val constructorId: String,
    val name: String,
    val nationality: String,
    val url: String
) {
    companion object {
        fun fromContentValues(values: ContentValues?): Constructor =
            Constructor(
                values?.getAsLong(Constructor::_id.name),
                values!!.getAsString(Constructor::constructorId.name),
                values.getAsString(Constructor::name.name),
                values.getAsString(Constructor::nationality.name),
                values.getAsString(Constructor::url.name),
            )
    }
}
