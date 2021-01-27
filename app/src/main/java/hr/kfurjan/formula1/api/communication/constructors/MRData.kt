package hr.kfurjan.formula1.api.communication.constructors

import com.google.gson.annotations.SerializedName

data class MRData(
    @SerializedName("ConstructorTable")
    val constructorTable: ConstructorTable,
    @SerializedName("limit")
    val limit: String,
    @SerializedName("offset")
    val offset: String,
    @SerializedName("series")
    val series: String,
    @SerializedName("total")
    val total: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("xmlns")
    val xmlns: String
)
