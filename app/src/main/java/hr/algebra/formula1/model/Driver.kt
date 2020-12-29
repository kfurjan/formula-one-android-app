package hr.algebra.formula1.model

data class Driver(
    val _id: Long?,
    val driverId: String,
    val firstName: String,
    val lastName: String,
    val url: String,
    val birthDate: String,
    val nationality: String,
    val code: String?,
    val number: String?
)
