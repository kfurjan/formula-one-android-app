package hr.algebra.formula1.api.communication.circuits

import com.google.gson.annotations.SerializedName

data class CircuitTable(
    @SerializedName("Circuits")
    val circuits: List<Circuit>
)
