package hr.kfurjan.formula1.api.communication.seasons

import com.google.gson.annotations.SerializedName

data class SeasonApi(
    @SerializedName("MRData")
    val mRData: MRData
)
