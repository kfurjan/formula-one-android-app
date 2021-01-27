package hr.kfurjan.formula1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.kfurjan.formula1.extensions.startActivity
import hr.kfurjan.formula1.model.Circuit

class CircuitAdapter(private val context: Context) :
    RecyclerView.Adapter<CircuitAdapter.ViewHolder>() {

    private var circuits: List<Circuit> = listOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvCircuitName: TextView = itemView.findViewById(R.id.tvCircuitName)
        private val tvCircuitInformation: TextView =
            itemView.findViewById(R.id.tvCircuitInformation)

        fun bind(circuit: Circuit) {
            tvCircuitName.text = circuit.name

            val circuitInformation = "${circuit.countryName}, ${circuit.localName}"
            tvCircuitInformation.text = circuitInformation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.circuit, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            context.startActivity<WebViewActivity>(URL, circuits[position].url)
        }
        holder.bind(circuits[position])
    }

    override fun getItemCount() = circuits.size

    fun setCircuits(circuits: List<Circuit>) {
        this.circuits = circuits
        notifyDataSetChanged()
    }
}
