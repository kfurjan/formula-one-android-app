package hr.algebra.formula1.model

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Driver(
    @PrimaryKey(autoGenerate = true) val _id: Long?,
    val driverId: String,
    val firstName: String,
    val lastName: String,
    val url: String,
    val birthDate: String,
    val nationality: String,
    val code: String?,
    val number: String?
) {
    companion object {
        fun fromContentValues(values: ContentValues?): Driver =
            Driver(
                values?.getAsLong(Driver::_id.name),
                values!!.getAsString(Driver::driverId.name),
                values.getAsString(Driver::firstName.name),
                values.getAsString(Driver::lastName.name),
                values.getAsString(Driver::url.name),
                values.getAsString(Driver::birthDate.name),
                values.getAsString(Driver::nationality.name),
                values.getAsString(Driver::code.name),
                values.getAsString(Driver::number.name)
            )
    }
}
