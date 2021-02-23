package hr.kfurjan.formula1.api

import hr.kfurjan.formula1.BuildConfig.API_LIMIT
import hr.kfurjan.formula1.api.communication.circuits.CircuitApi
import hr.kfurjan.formula1.api.communication.constructors.ConstructorApi
import hr.kfurjan.formula1.api.communication.drivers.DriverApi
import hr.kfurjan.formula1.api.communication.seasons.SeasonApi
import retrofit2.Call
import retrofit2.http.GET

interface Formula1DataApi {
    @GET("drivers.json?limit=$API_LIMIT")
    fun fetchDrivers(): Call<DriverApi>

    @GET("constructors.json?limit=$API_LIMIT")
    fun fetchConstructors(): Call<ConstructorApi>

    @GET("seasons.json?limit=$API_LIMIT")
    fun fetchSeasons(): Call<SeasonApi>

    @GET("circuits.json?limit=$API_LIMIT")
    fun fetchCircuits(): Call<CircuitApi>
}
