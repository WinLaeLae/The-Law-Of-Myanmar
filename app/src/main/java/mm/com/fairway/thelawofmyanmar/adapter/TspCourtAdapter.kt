package mm.com.fairway.thelawofmyanmar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_tsp_court_office.view.*
import mm.com.fairway.thelawofmyanmar.R
import mm.com.fairway.thelawofmyanmar.ui.townshipCourt.Court

class TspCourtAdapter(var courtList:ArrayList<Court> = ArrayList()) :RecyclerView.Adapter<TspCourtAdapter.TspCourtViewHolder>(){
    var clickListener:ClickListener? = null

    fun setOnClickListener(clickListener: ClickListener){
        this.clickListener = clickListener
    }
    fun  updateTspCourtList(courtList: ArrayList<Court>){
        this.courtList= courtList
        notifyDataSetChanged()
    }
 inner class   TspCourtViewHolder(item: View): RecyclerView.ViewHolder(item),View.OnClickListener{
     private lateinit var court: Court
     init {
         item.setOnClickListener(this)
     }
     fun bind(court: Court){
         this.court = court
         itemView.tsp_courtName_txt.text = court.name
         itemView.courtAddress_txt.text= court.address
         itemView.phoneNo_txt.text= court.phone
     }

     override fun onClick(v: View?) {
        clickListener?.onClick(court)
     }
 }
    interface ClickListener{
        fun onClick(court: Court)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TspCourtViewHolder {
        return TspCourtViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tsp_court_office,parent,false))
    }

    override fun onBindViewHolder(holder: TspCourtViewHolder, position: Int) {
        holder.bind(courtList.get(position))
    }

    override fun getItemCount(): Int {
       return courtList.size
    }
}