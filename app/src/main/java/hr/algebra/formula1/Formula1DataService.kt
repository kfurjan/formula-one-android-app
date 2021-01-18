package hr.algebra.formula1

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.algebra.formula1.api.Formula1DataFetcher
import hr.algebra.formula1.extensions.setBooleanPreference

private const val JOB_ID = 1

class Formula1DataService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        applicationContext.setBooleanPreference(DATA_IMPORTED, true)
        Formula1DataFetcher(this).fetchData()
    }

    companion object {
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, Formula1DataService::class.java, JOB_ID, intent)
        }
    }
}
