package hr.kfurjan.formula1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.kfurjan.formula1.extensions.setBooleanPreference
import hr.kfurjan.formula1.extensions.startActivity

class Formula1DataReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        context.setBooleanPreference(DATA_IMPORTED, true)
        context.startActivity<HostActivity>()
    }
}
