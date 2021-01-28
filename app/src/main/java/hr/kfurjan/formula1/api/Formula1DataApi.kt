package hr.kfurjan.formula1.api

import hr.kfurjan.formula1.api.communication.circuits.CircuitApi
import hr.kfurjan.formula1.api.communication.constructors.ConstructorApi
import hr.kfurjan.formula1.api.communication.drivers.DriverApi
import hr.kfurjan.formula1.api.communication.seasons.SeasonApi
import retrofit2.Call
import retrofit2.http.GET

const val LIMIT = 1000

interface Formula1DataApi {
    @GET("drivers.json?limit=$LIMIT")
    fun fetchDrivers(): Call<DriverApi>

    @GET("constructors.json?limit=$LIMIT")
    fun fetchConstructors(): Call<ConstructorApi>

    @GET("seasons.json?limit=$LIMIT")
    fun fetchSeasons(): Call<SeasonApi>

    @GET("circuits.json?limit=$LIMIT")
    fun fetchCircuits(): Call<CircuitApi>
}
