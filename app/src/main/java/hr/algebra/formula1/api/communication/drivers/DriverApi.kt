package hr.algebra.formula1.api.communication.drivers

import com.google.gson.annotations.SerializedName

data class DriverApi(
    @SerializedName("MRData")
    val mRData: MRData
)
