package au.com.cognizant.cognizantdev.feature.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import au.com.cognizant.cognizantdev.feature.model.Fact
import butterknife.ButterKnife

class AboutCanadaAdapter(internal val factList: MutableList<Fact> = mutableListOf()) :
        RecyclerView.Adapter<AboutCanadaAdapter.FactViewHolder>() {

    override fun getItemCount(): Int = factList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return FactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        //Todo bind dataset to view
    }

    class FactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            ButterKnife.bind(this, itemView)
        }
    }
}