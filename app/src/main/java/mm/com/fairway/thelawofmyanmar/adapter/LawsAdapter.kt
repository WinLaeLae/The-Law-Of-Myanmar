package mm.com.fairway.thelawofmyanmar.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_department_list.view.*
import mm.com.fairway.thelawofmyanmar.R
import mm.com.fairway.thelawofmyanmar.api.ApiClient
import mm.com.fairway.thelawofmyanmar.ui.GovWebList.Department

class LawsAdapter(var departmentlist: ArrayList<Department> = ArrayList()): RecyclerView.Adapter<LawsAdapter.LawsDepartmentViewHolder>(){
var clickListener:ClickListener? = null
    fun setOnClickListener(clickListener: ClickListener){
        this.clickListener= clickListener
    }
    fun updateDepartmentList(departmentlist:ArrayList<Department>){
        this.departmentlist= departmentlist
        notifyDataSetChanged()
    }
inner class LawsDepartmentViewHolder(item: View) : RecyclerView.ViewHolder(item),View.OnClickListener {
lateinit var department: Department
    init {
        item.setOnClickListener(this)
    }
    fun bind(department: Department) {
        this.department=department
        Picasso.get().load(ApiClient.imageUrl + department.image).placeholder(R.drawable.gov)
            .into(itemView.departLogo_img);
        itemView.departName_txt.text = department.name
    }

    override fun onClick(p0: View?) {
        clickListener?.OnClick(department)
    }

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LawsDepartmentViewHolder {
      return  LawsDepartmentViewHolder(LayoutInflater.from(parent.context)
          .inflate(R.layout.item_department_list,parent,false))
    }

    override fun onBindViewHolder(holder: LawsDepartmentViewHolder, position: Int) {
        holder.bind(departmentlist.get(position))
    }

    override fun getItemCount(): Int {
      return departmentlist.size
    }
    interface ClickListener{
        fun OnClick(department: Department)
    }
}