package hr.algebra.formula1.extensions

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

inline fun <reified T : Activity> Context.startActivity() =
        startActivity(Intent(this, T::class.java))

inline fun <reified T : BroadcastReceiver> Context.sendBroadcast() =
        sendBroadcast(Intent(this, T::class.java))