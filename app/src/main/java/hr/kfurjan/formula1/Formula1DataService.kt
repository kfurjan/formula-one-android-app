package hr.kfurjan.formula1

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import dagger.hilt.android.AndroidEntryPoint
import hr.kfurjan.formula1.BuildConfig.JOB_ID
import hr.kfurjan.formula1.api.Formula1DataFetcher
import javax.inject.Inject

@AndroidEntryPoint
class Formula1DataService : JobIntentService() {

    @Inject
    lateinit var formula1DataFetcher: Formula1DataFetcher

    override fun onHandleWork(intent: Intent) = formula1DataFetcher.fetchData()

    companion object {
        fun enqueueWork(context: Context, intent: Intent) =
            enqueueWork(context, Formula1DataService::class.java, JOB_ID, intent)
    }
}
