package hr.algebra.formula1.api.communication.drivers

import com.google.gson.annotations.SerializedName

data class DriverTable(
    @SerializedName("Drivers")
    val drivers: List<Driver>
)
