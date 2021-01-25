package hr.algebra.formula1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.formula1.extensions.startActivity
import hr.algebra.formula1.model.Constructor

class ConstructorsAdapter(private val context: Context) :
    RecyclerView.Adapter<ConstructorsAdapter.ViewHolder>() {

    private var constructors: List<Constructor> = listOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvConstructorName: TextView = itemView.findViewById(R.id.tvConstructorName)
        private val tvConstructorNationality: TextView =
            itemView.findViewById(R.id.tvConstructorNationality)

        fun bind(constructor: Constructor) {
            tvConstructorName.text = constructor.name
            tvConstructorNationality.text = constructor.nationality
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.constructor, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            context.startActivity<WebViewActivity>(URL, constructors[position].url)
        }
        holder.bind(constructors[position])
    }

    override fun getItemCount() = constructors.size

    fun setConstructors(constructors: List<Constructor>) {
        this.constructors = constructors
        notifyDataSetChanged()
    }
}
