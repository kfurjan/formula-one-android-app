package hr.algebra.formula1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.formula1.model.Driver

class DriverAdapter(private val drivers: MutableList<Driver>) :
    RecyclerView.Adapter<DriverAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDriverFullName: TextView = itemView.findViewById(R.id.tvDriverFullName)
        private val tvDriverCode: TextView = itemView.findViewById(R.id.tvDriverCode)
        private val tvDriverNationality: TextView = itemView.findViewById(R.id.tvDriverNationality)
        private val tvDriverBirthDate: TextView = itemView.findViewById(R.id.tvDriverBirthDate)

        fun bind(driver: Driver) {
            val driverFullName = "${driver.firstName} ${driver.lastName}"
            tvDriverFullName.text = driverFullName

            val driverCode = if (driver.code != null) "(${driver.code})" else ""
            tvDriverCode.text = driverCode
            tvDriverNationality.text = driver.nationality
            tvDriverBirthDate.text = driver.birthDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.driver, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(drivers[position])

    override fun getItemCount() = drivers.size
}
