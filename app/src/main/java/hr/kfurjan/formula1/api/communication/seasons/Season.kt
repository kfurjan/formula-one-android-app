package hr.kfurjan.formula1.api.communication.seasons

import com.google.gson.annotations.SerializedName

data class Season(
    @SerializedName("season")
    val season: String,
    @SerializedName("url")
    val url: String
)
