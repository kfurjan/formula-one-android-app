package hr.kfurjan.formula1.api.communication.constructors

import com.google.gson.annotations.SerializedName

data class ConstructorTable(
    @SerializedName("Constructors")
    val constructors: List<Constructor>
)
