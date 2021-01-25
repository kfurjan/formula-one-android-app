package hr.algebra.formula1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.formula1.extensions.startActivity
import hr.algebra.formula1.model.Season

class SeasonAdapter(private val context: Context) :
    RecyclerView.Adapter<SeasonAdapter.ViewHolder>() {

    private var seasons: List<Season> = listOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvSeasonYear: TextView = itemView.findViewById(R.id.tvSeasonYear)

        fun bind(season: Season) {
            tvSeasonYear.text = season.year
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.season, parent, false)
        )
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            context.startActivity<WebViewActivity>(URL, seasons[position].url)
        }
        holder.bind(seasons[position])
    }

    override fun getItemCount() = seasons.size

    fun setSeasons(seasons: List<Season>) {
        this.seasons = seasons
        notifyDataSetChanged()
    }
}
