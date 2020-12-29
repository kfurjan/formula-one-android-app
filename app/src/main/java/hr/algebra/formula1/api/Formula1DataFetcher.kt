package hr.algebra.formula1.api

import android.content.Context
import android.util.Log
import hr.algebra.formula1.Formula1DataReceiver
import hr.algebra.formula1.api.communication.drivers.DriverApi
import hr.algebra.formula1.extensions.sendBroadcast
import hr.algebra.formula1.model.Driver
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Formula1DataFetcher(private val context: Context) {

    private lateinit var formula1DataApi: Formula1DataApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        formula1DataApi = retrofit.create(Formula1DataApi::class.java)
    }

    fun fetchDrivers() {
        val request = formula1DataApi.fetchDrivers()
        request.enqueue(object : Callback<DriverApi> {
            override fun onResponse(
                call: Call<DriverApi>,
                response: Response<DriverApi>
            ) {
                if (response.body() != null) {
                    populateDrivers(response.body() as DriverApi)
                }
            }

            override fun onFailure(call: Call<DriverApi>, t: Throwable) {
                Log.d(javaClass.name, t.message, t)
            }
        })
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

            context.sendBroadcast<Formula1DataReceiver>()
        }
    }
}