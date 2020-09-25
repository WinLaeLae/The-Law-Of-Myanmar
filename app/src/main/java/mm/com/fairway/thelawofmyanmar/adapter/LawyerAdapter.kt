package mm.com.fairway.thelawofmyanmar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_lawyer_list.view.*
import mm.com.fairway.thelawofmyanmar.R
import mm.com.fairway.thelawofmyanmar.ui.lawyer.Lawyer

class LawyerAdapter(var lawyersList: ArrayList<Lawyer> = ArrayList()) :
    RecyclerView.Adapter<LawyerAdapter.LawyerViewHolder>() {
    private var clickListener: ClickListener? = null
    fun updateLawywrList(lawyerlist:ArrayList<Lawyer>){
        this.lawyersList= lawyerlist
        notifyDataSetChanged()
    }

    inner class LawyerViewHolder(item: View) : RecyclerView.ViewHolder(item), View.OnClickListener {
        private lateinit var lawyer: Lawyer

        init {
            item.setOnClickListener(this)
        }

        fun bind(lawyer: Lawyer) {
            this.lawyer = lawyer
            itemView.lawyerName_txt.text = lawyer.name
            itemView.lawyerNo__txt.text = lawyer.lawyer_no
            itemView.lawyerPosition_txt.text = lawyer.position
            itemView.lawyer_address_txt.text = lawyer.address
            itemView.lawyer_phNo_txt.text = lawyer.phone
        }

        override fun onClick(v: View?) {
            clickListener?.onClick(lawyer)
        }
    }

    fun setOnClickListener(clickListener: ClickListener) {
        this.clickListener= clickListener
    }

    interface ClickListener {
        fun onClick(lawyer: Lawyer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LawyerViewHolder {
        return LawyerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_lawyer_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LawyerViewHolder, position: Int) {
        holder.bind(lawyersList.get(position))
    }

    override fun getItemCount(): Int {
        return lawyersList.size
    }
}