package hr.algebra.formula1.api

import hr.algebra.formula1.api.communication.circuits.CircuitApi
import hr.algebra.formula1.api.communication.constructors.ConstructorApi
import hr.algebra.formula1.api.communication.drivers.DriverApi
import hr.algebra.formula1.api.communication.seasons.SeasonApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

const val API_URL = "http://ergast.com/api/f1/"
const val LIMIT = 1000

interface Formula1DataApi {

    @GET("drivers.json?limit=$LIMIT")
    fun fetchDrivers(): Call<DriverApi>

    @GET("constructors.json?limit=$LIMIT")
    fun fetchConstructors(): Call<List<ConstructorApi>>

    @GET("seasons.json?limit=$LIMIT")
    fun fetchSeasons(): Call<List<SeasonApi>>

    @GET("circuits.json?limit=$LIMIT")
    fun fetchCircuits(): Call<List<CircuitApi>>

    @GET("{data}.json?limit=$LIMIT")
    fun <T> fetchData(@Path("data") data: String): Call<List<T>>
}