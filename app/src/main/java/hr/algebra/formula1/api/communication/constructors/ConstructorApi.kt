package hr.algebra.formula1.api.communication.constructors

import com.google.gson.annotations.SerializedName

data class ConstructorApi(
    @SerializedName("MRData")
    val mRData: MRData
)
