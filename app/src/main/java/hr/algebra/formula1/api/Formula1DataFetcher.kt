package hr.algebra.formula1.api

import android.content.Context
import android.util.Log
import hr.algebra.formula1.Formula1DataReceiver
import hr.algebra.formula1.api.communication.circuits.CircuitApi
import hr.algebra.formula1.api.communication.constructors.ConstructorApi
import hr.algebra.formula1.api.communication.drivers.DriverApi
import hr.algebra.formula1.api.communication.seasons.SeasonApi
import hr.algebra.formula1.extensions.sendBroadcast
import hr.algebra.formula1.model.Circuit
import hr.algebra.formula1.model.Constructor
import hr.algebra.formula1.model.Driver
import hr.algebra.formula1.model.Season
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Formula1DataFetcher(private val context: Context) {

    private var formula1DataApi: Formula1DataApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        formula1DataApi = retrofit.create(Formula1DataApi::class.java)
    }

    fun fetchData() {
        fetchDrivers()
        fetchConstructors()
        fetchSeasons()
        fetchCircuits()
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

    private fun populateDrivers(driversData: DriverApi) {
        GlobalScope.launch {
            val drivers: MutableList<Driver> = mutableListOf()

            driversData.mRData.driverTable.drivers.forEach {
                drivers.add(
                    Driver(
                        null,
                        it.driverId,
                        it.givenName,
                        it.familyName,
                        it.url,
                        it.dateOfBirth,
                        it.nationality,
                        it.code,
                        it.permanentNumber
                    )
                )
            }
        }
    }

    private fun populateConstructors(constructorsData: ConstructorApi) {
        GlobalScope.launch {
            val constructors: MutableList<Constructor> = mutableListOf()

            constructorsData.mRData.constructorTable.constructors.forEach {
                constructors.add(
                    Constructor(null, it.constructorId, it.name, it.nationality, it.nationality)
                )
            }
        }
    }

    private fun populateSeasons(seasonsData: SeasonApi) {
        GlobalScope.launch {
            val seasons: MutableList<Season> = mutableListOf()

            seasonsData.mRData.seasonTable.seasons.forEach {
                seasons.add(
                    Season(null, it.season, it.url)
                )
            }
        }
    }

    private fun populateCircuits(circuitsData: CircuitApi) {
        GlobalScope.launch {
            val circuits: MutableList<Circuit> = mutableListOf()

            circuitsData.mRData.circuitTable.circuits.forEach {
                circuits.add(
                    Circuit(
                        null,
                        it.circuitId,
                        it.circuitName,
                        it.location.lat,
                        it.location.long,
                        it.location.locality,
                        it.location.country,
                        it.url
                    )
                )
            }

            context.sendBroadcast<Formula1DataReceiver>()
        }
    }
}
