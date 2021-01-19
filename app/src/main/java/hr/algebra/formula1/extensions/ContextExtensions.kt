package hr.algebra.formula1.extensions

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.preference.PreferenceManager
import hr.algebra.formula1.CIRCUIT_CONTENT_PROVIDER_URI
import hr.algebra.formula1.CONSTRUCTOR_CONTENT_PROVIDER_URI
import hr.algebra.formula1.DRIVER_CONTENT_PROVIDER_URI
import hr.algebra.formula1.model.Circuit
import hr.algebra.formula1.model.Constructor
import hr.algebra.formula1.model.Driver
import hr.algebra.formula1.model.Season

inline fun <reified T : Activity> Context.startActivity() =
    startActivity(
        Intent(this, T::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    )

inline fun <reified T : BroadcastReceiver> Context.sendBroadcast() =
    sendBroadcast(Intent(this, T::class.java))

fun Context.isOnline(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork

    if (network != null) {
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        if (networkCapabilities != null) {
            return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
    }

    return false
}

fun Context.setBooleanPreference(key: String, value: Boolean) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putBoolean(key, value)
        .apply()

fun Context.getBooleanPreference(key: String) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .getBoolean(key, false)

fun Context.fetchDrivers(): MutableList<Driver> {
    val drivers = mutableListOf<Driver>()
    val cursor = contentResolver?.query(
        DRIVER_CONTENT_PROVIDER_URI,
        null, null, null, null, null
    )

    if (cursor != null) {
        while (cursor.moveToNext()) {
            drivers.add(
                Driver(
                    cursor.getLong(cursor.getColumnIndex(Driver::_id.name)),
                    cursor.getString(cursor.getColumnIndex(Driver::driverId.name)),
                    cursor.getString(cursor.getColumnIndex(Driver::firstName.name)),
                    cursor.getString(cursor.getColumnIndex(Driver::lastName.name)),
                    cursor.getString(cursor.getColumnIndex(Driver::url.name)),
                    cursor.getString(cursor.getColumnIndex(Driver::birthDate.name)),
                    cursor.getString(cursor.getColumnIndex(Driver::nationality.name)),
                    cursor.getString(cursor.getColumnIndex(Driver::code.name)),
                    cursor.getString(cursor.getColumnIndex(Driver::number.name))
                )
            )
        }
    }
    cursor?.close()

    return drivers
}

fun Context.fetchCircuits(): MutableList<Circuit> {
    val circuits = mutableListOf<Circuit>()
    val cursor = contentResolver?.query(
        CIRCUIT_CONTENT_PROVIDER_URI,
        null, null, null, null, null
    )

    if (cursor != null) {
        while (cursor.moveToNext()) {
            circuits.add(
                Circuit(
                    cursor.getLong(cursor.getColumnIndex(Circuit::_id.name)),
                    cursor.getString(cursor.getColumnIndex(Circuit::circuitId.name)),
                    cursor.getString(cursor.getColumnIndex(Circuit::name.name)),
                    cursor.getString(cursor.getColumnIndex(Circuit::latitude.name)),
                    cursor.getString(cursor.getColumnIndex(Circuit::longitude.name)),
                    cursor.getString(cursor.getColumnIndex(Circuit::localName.name)),
                    cursor.getString(cursor.getColumnIndex(Circuit::countryName.name)),
                    cursor.getString(cursor.getColumnIndex(Circuit::url.name)),
                )
            )
        }
    }
    cursor?.close()

    return circuits
}

fun Context.fetchConstructors(): MutableList<Constructor> {
    val constructors = mutableListOf<Constructor>()
    val cursor = contentResolver?.query(
        CONSTRUCTOR_CONTENT_PROVIDER_URI,
        null, null, null, null, null
    )

    if (cursor != null) {
        while (cursor.moveToNext()) {
            constructors.add(
                Constructor(
                    cursor.getLong(cursor.getColumnIndex(Constructor::_id.name)),
                    cursor.getString(cursor.getColumnIndex(Constructor::constructorId.name)),
                    cursor.getString(cursor.getColumnIndex(Constructor::name.name)),
                    cursor.getString(cursor.getColumnIndex(Constructor::nationality.name)),
                    cursor.getString(cursor.getColumnIndex(Constructor::url.name)),
                )
            )
        }
    }
    cursor?.close()

    return constructors
}

fun Context.fetchSeasons(): MutableList<Season> {
    val seasons = mutableListOf<Season>()
    val cursor = contentResolver?.query(
        CONSTRUCTOR_CONTENT_PROVIDER_URI,
        null, null, null, null, null
    )

    if (cursor != null) {
        while (cursor.moveToNext()) {
            seasons.add(
                Season(
                    cursor.getLong(cursor.getColumnIndex(Season::_id.name)),
                    cursor.getString(cursor.getColumnIndex(Season::year.name)),
                    cursor.getString(cursor.getColumnIndex(Season::url.name)),
                )
            )
        }
    }
    cursor?.close()

    return seasons
}
