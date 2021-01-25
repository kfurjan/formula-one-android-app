package hr.algebra.formula1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.formula1.extensions.startActivity
import hr.algebra.formula1.model.Driver

class DriverAdapter(private val context: Context) :
    RecyclerView.Adapter<DriverAdapter.ViewHolder>() {

    private var drivers: List<Driver> = listOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDriverInformation: TextView = itemView.findViewById(R.id.tvDriverInformation)
        private val tvDriverNationality: TextView = itemView.findViewById(R.id.tvDriverNationality)
        private val tvDriverBirthDate: TextView = itemView.findViewById(R.id.tvDriverBirthDate)

        fun bind(driver: Driver) {
            val driverInformation =
                if (driver.code != null) {
                    "${driver.firstName} ${driver.lastName} (${driver.code})"
                } else {
                    "${driver.firstName} ${driver.lastName}"
                }
            tvDriverInformation.text = driverInformation

            tvDriverNationality.text = driver.nationality
            tvDriverBirthDate.text = driver.birthDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.driver, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            context.startActivity<WebViewActivity>(URL, drivers[position].url)
        }
        holder.bind(drivers[position])
    }

    override fun getItemCount() = drivers.size

    fun setDrivers(drivers: List<Driver>) {
        this.drivers = drivers
        notifyDataSetChanged()
    }
}
