package hr.algebra.formula1.api

import android.content.ContentValues
import android.content.Context
import android.util.Log
import hr.algebra.formula1.CIRCUIT_CONTENT_PROVIDER_URI
import hr.algebra.formula1.CONSTRUCTOR_CONTENT_PROVIDER_URI
import hr.algebra.formula1.DRIVER_CONTENT_PROVIDER_URI
import hr.algebra.formula1.Formula1DataReceiver
import hr.algebra.formula1.SEASON_CONTENT_PROVIDER_URI
import hr.algebra.formula1.api.communication.circuits.CircuitApi
import hr.algebra.formula1.api.communication.constructors.ConstructorApi
import hr.algebra.formula1.api.communication.drivers.DriverApi
import hr.algebra.formula1.api.communication.seasons.SeasonApi
import hr.algebra.formula1.extensions.sendBroadcast
import hr.algebra.formula1.model.Circuit
import hr.algebra.formula1.model.Constructor
import hr.algebra.formula1.model.Driver
import hr.algebra.formula1.model.Season
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Formula1DataFetcher(private val context: Context) {

    private val scope = CoroutineScope(Dispatchers.IO)
    private val formula1DataApi: Formula1DataApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        formula1DataApi = retrofit.create(Formula1DataApi::class.java)
    }

    fun fetchData() {
        fetchCircuits()
        fetchConstructors()
        fetchSeasons()
        fetchDrivers()
    }

    private fun fetchDrivers() = fetchApiData(formula1DataApi.fetchDrivers())
    private fun fetchConstructors() = fetchApiData(formula1DataApi.fetchConstructors())
    private fun fetchCircuits() = fetchApiData(formula1DataApi.fetchCircuits())
    private fun fetchSeasons() = fetchApiData(formula1DataApi.fetchSeasons())

    private fun <T> fetchApiData(request: Call<T>) {
        request.enqueue(object : Callback<T> {
            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                if (response.body() != null) {
                    populateData(response.body())
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                Log.d(javaClass.name, t.message, t)
            }
        })
    }

    private fun <T> populateData(data: T) =
        when (data) {
            is DriverApi -> populateDrivers(data)
            is ConstructorApi -> populateConstructors(data)
            is SeasonApi -> populateSeasons(data)
            is CircuitApi -> populateCircuits(data)
            else -> throw IllegalArgumentException("$data is not recognized")
        }

    private fun populateCircuits(circuitsData: CircuitApi) {
        scope.launch {
            circuitsData.mRData.circuitTable.circuits.forEach {
                context.contentResolver.insert(
                    CIRCUIT_CONTENT_PROVIDER_URI,
                    ContentValues().apply {
                        put(Circuit::circuitId.name, it.circuitId)
                        put(Circuit::name.name, it.circuitName)
                        put(Circuit::latitude.name, it.location.lat)
                        put(Circuit::longitude.name, it.location.long)
                        put(Circuit::localName.name, it.location.locality)
                        put(Circuit::countryName.name, it.location.country)
                        put(Circuit::url.name, it.url)
                    }
                )
            }
        }
    }

    private fun populateConstructors(constructorsData: ConstructorApi) {
        scope.launch {
            constructorsData.mRData.constructorTable.constructors.forEach {
                context.contentResolver.insert(
                    CONSTRUCTOR_CONTENT_PROVIDER_URI,
                    ContentValues().apply {
                        put(Constructor::constructorId.name, it.constructorId)
                        put(Constructor::name.name, it.name)
                        put(Constructor::nationality.name, it.nationality)
                        put(Constructor::url.name, it.url)
                    }
                )
            }
        }
    }

    private fun populateSeasons(seasonsData: SeasonApi) {
        scope.launch {
            seasonsData.mRData.seasonTable.seasons.forEach {
                context.contentResolver.insert(
                    SEASON_CONTENT_PROVIDER_URI,
                    ContentValues().apply {
                        put(Season::year.name, it.season)
                        put(Season::url.name, it.url)
                    }
                )
            }
        }
    }

    private fun populateDrivers(driversData: DriverApi) {
        scope.launch {
            driversData.mRData.driverTable.drivers.forEach {
                context.contentResolver.insert(
                    DRIVER_CONTENT_PROVIDER_URI,
                    ContentValues().apply {
                        put(Driver::driverId.name, it.driverId)
                        put(Driver::firstName.name, it.givenName)
                        put(Driver::lastName.name, it.familyName)
                        put(Driver::url.name, it.url)
                        put(Driver::birthDate.name, it.dateOfBirth)
                        put(Driver::nationality.name, it.nationality)
                        put(Driver::code.name, it.code)
                        put(Driver::number.name, it.permanentNumber)
                    }
                )
            }

            context.sendBroadcast<Formula1DataReceiver>()
        }
    }
}
