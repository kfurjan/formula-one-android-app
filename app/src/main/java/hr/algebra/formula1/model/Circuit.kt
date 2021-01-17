package hr.algebra.formula1.model

import android.content.ContentValues
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
) {
    companion object {
        fun fromContentValues(values: ContentValues?): Circuit =
            Circuit(
                values?.getAsLong(Circuit::_id.name),
                values!!.getAsString(Circuit::circuitId.name),
                values.getAsString(Circuit::name.name),
                values.getAsString(Circuit::latitude.name),
                values.getAsString(Circuit::longitude.name),
                values.getAsString(Circuit::localName.name),
                values.getAsString(Circuit::countryName.name),
                values.getAsString(Circuit::url.name),
            )
    }
}
