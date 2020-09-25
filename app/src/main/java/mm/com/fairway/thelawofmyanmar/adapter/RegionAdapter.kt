package mm.com.fairway.thelawofmyanmar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_region_name_layout.view.*
import mm.com.fairway.thelawofmyanmar.R
import mm.com.fairway.thelawofmyanmar.ui.region.Region

class RegionAdapter(var regionList: ArrayList<Region> = ArrayList()) :
    RecyclerView.Adapter<RegionAdapter.RegionViewHolder>() {
    var clickListener:ClickListener? = null
    fun setOnClickListener(clickListener: ClickListener)
    {
        this.clickListener= clickListener
    }
    fun updateRegionList(regionList: ArrayList<Region>){
        this .regionList= regionList
        notifyDataSetChanged()
    }
inner class RegionViewHolder(item: View) : RecyclerView.ViewHolder(item),View.OnClickListener {
        lateinit var region: Region
    init {
        item.setOnClickListener(this)
    }
        fun bind(reggion: Region) {
            this.region = reggion
            itemView.region_Name_txt.text = reggion.name
        }

    override fun onClick(v: View?) {
        clickListener?.onClick(region)
    }
}

    interface ClickListener {
        fun onClick(region: Region)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
        return RegionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_region_name_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
      holder.bind(regionList.get(position))
    }

    override fun getItemCount(): Int {
       return regionList.size
    }
}