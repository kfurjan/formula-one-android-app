package hr.algebra.formula1.api.communication.circuits

import com.google.gson.annotations.SerializedName

data class CircuitApi(
    @SerializedName("MRData")
    val mRData: MRData
)
