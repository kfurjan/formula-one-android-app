package hr.kfurjan.formula1.api.communication.seasons

import com.google.gson.annotations.SerializedName

data class SeasonTable(
    @SerializedName("Seasons")
    val seasons: List<Season>
)
