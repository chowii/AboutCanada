package au.com.cognizant.cognizantdev.feature.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import au.com.cognizant.cognizantdev.R
import au.com.cognizant.cognizantdev.feature.model.Fact
import butterknife.BindView
import butterknife.ButterKnife
import com.squareup.picasso.Picasso

class AboutCanadaAdapter(internal val factList: MutableList<Fact> = mutableListOf()) :
        RecyclerView.Adapter<AboutCanadaAdapter.FactViewHolder>() {

    override fun getItemCount(): Int = factList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_fact, parent, false)
        return FactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.onBindViewHolder(factList[holder.adapterPosition])
    }

    /**
     * Updates dataset for recyclerview by first clearing existing dataset, and adding all provided
     * new dataset
     */
    fun updateDataset(factList: MutableList<Fact>) {
        this.factList.apply {
            clear()
            addAll(factList)
        }
    }

    class FactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.subtitle)
        lateinit var subtitle: TextView

        @BindView(R.id.header)
        lateinit var header: TextView

        @BindView(R.id.image)
        lateinit var imageView: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

        /**
         * Binds view with dataset provided to the adapter
         */
        fun onBindViewHolder(item: Fact) {
            item.apply {
                if (title == null) {
                    header.visibility = GONE
                    header.text = ""
                } else {
                    header.visibility = VISIBLE
                    header.text = title
                }
                if (description == null) {
                    subtitle.visibility = GONE
                    subtitle.text = ""
                } else {
                    subtitle.visibility = VISIBLE
                    subtitle.text = description
                }
                if (imageHref == null) {
                    imageView.setImageResource(R.drawable.ic_error)
                } else {
                    val resizeWidth = 500
                    val resizeHeight = 250
                    Picasso.Builder(itemView.context)
                            .build()
                            .load(imageHref)
                            .error(R.drawable.ic_error)
                            .placeholder(R.drawable.ic_error)
                            .resize(resizeWidth, resizeHeight)
                            .centerCrop()
                            .into(imageView)
                }
            }
        }
    }
}