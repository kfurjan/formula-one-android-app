package hr.algebra.formula1.model

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Season(
    @PrimaryKey(autoGenerate = true) val _id: Long?,
    val year: String,
    val url: String
) {
    companion object {
        fun fromContentValues(values: ContentValues?): Season =
            Season(
                values?.getAsLong(Season::_id.name),
                values!!.getAsString(Season::year.name),
                values.getAsString(Season::url.name),
            )
    }
}
