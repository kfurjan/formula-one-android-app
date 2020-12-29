package hr.algebra.formula1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.formula1.extensions.startActivity

class Formula1DataReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        context.startActivity<HostActivity>()
    }
}