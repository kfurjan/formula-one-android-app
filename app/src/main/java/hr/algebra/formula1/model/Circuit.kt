package hr.algebra.formula1.model

data class Circuit(
    val _id: Long?,
    val circuitId: String,
    val name: String,
    val latitude: String,
    val longitude: String,
    val localName: String,
    val countryName: String,
    val url: String
)
