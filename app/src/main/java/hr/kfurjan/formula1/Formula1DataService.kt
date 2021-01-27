package hr.kfurjan.formula1

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.kfurjan.formula1.api.Formula1DataFetcher
import javax.inject.Inject

private const val JOB_ID = 1

class Formula1DataService @Inject constructor(private val formula1DataFetcher: Formula1DataFetcher) :
    JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        formula1DataFetcher.fetchData()
    }

    companion object {
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, Formula1DataService::class.java, JOB_ID, intent)
        }
    }
}
