package hr.kfurjan.formula1.api

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import hr.kfurjan.formula1.Formula1DataReceiver
import hr.kfurjan.formula1.api.communication.circuits.CircuitApi
import hr.kfurjan.formula1.api.communication.constructors.ConstructorApi
import hr.kfurjan.formula1.api.communication.drivers.DriverApi
import hr.kfurjan.formula1.api.communication.seasons.SeasonApi
import hr.kfurjan.formula1.extensions.sendBroadcast
import hr.kfurjan.formula1.model.Circuit
import hr.kfurjan.formula1.model.Constructor
import hr.kfurjan.formula1.model.Driver
import hr.kfurjan.formula1.model.Season
import hr.kfurjan.formula1.repository.CircuitRepository
import hr.kfurjan.formula1.repository.ConstructorRepository
import hr.kfurjan.formula1.repository.DriverRepository
import hr.kfurjan.formula1.repository.SeasonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Formula1DataFetcher @Inject constructor(
    @ApplicationContext private val context: Context,
    private val driverRepository: DriverRepository,
    private val circuitRepository: CircuitRepository,
    private val seasonRepository: SeasonRepository,
    private val constructorRepository: ConstructorRepository,
    private val formula1DataApi: Formula1DataApi
) {
    private val dataFetcherScope = CoroutineScope(Dispatchers.IO)

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
        request.enqueue(
            object : Callback<T> {
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
            }
        )
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
        dataFetcherScope.launch {
            circuitsData.mRData.circuitTable.circuits.forEach {
                circuitRepository.insert(
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
        }
    }

    private fun populateConstructors(constructorsData: ConstructorApi) {
        dataFetcherScope.launch {
            constructorsData.mRData.constructorTable.constructors.forEach {
                constructorRepository.insert(
                    Constructor(null, it.constructorId, it.name, it.nationality, it.url)
                )
            }
        }
    }

    private fun populateSeasons(seasonsData: SeasonApi) {
        dataFetcherScope.launch {
            seasonsData.mRData.seasonTable.seasons.forEach {
                seasonRepository.insert(Season(null, it.season, it.url))
            }
        }
    }

    private fun populateDrivers(driversData: DriverApi) {
        dataFetcherScope.launch {
            driversData.mRData.driverTable.drivers.forEach {
                driverRepository.insert(
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

            context.sendBroadcast<Formula1DataReceiver>()
        }
    }
}
