package mm.com.fairway.thelawofmyanmar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_gov_web_list.view.*
import mm.com.fairway.thelawofmyanmar.R
import mm.com.fairway.thelawofmyanmar.api.ApiClient
import mm.com.fairway.thelawofmyanmar.ui.GovWebList.Department

class DepartmentAdapter(var departmentList:ArrayList<Department> = ArrayList()): RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder>() {
    var clickListener: ClickListener? =null
fun setOnClickListener(clickListener: ClickListener){
    this.clickListener= clickListener
}
    fun updateDepartmentList(departmentList:ArrayList<Department>){
        this.departmentList=departmentList
        notifyDataSetChanged()
    }

 inner class  DepartmentViewHolder(item: View): RecyclerView.ViewHolder(item),View.OnClickListener{
lateinit var department: Department
      init {
          item.setOnClickListener(this)
      }
      fun bind(department: Department){
          this.department= department
         Picasso.get().load(ApiClient.imageUrl+department.image).placeholder(R.drawable.gov).into(itemView.image_logo_GOV)
      itemView.govName_txt.text = department.name
      }

      override fun onClick(v: View?) {
         clickListener?.onClick(department)
      }
  }
    interface ClickListener{
        fun onClick(department: Department)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        return DepartmentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_gov_web_list,parent,false))
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
       holder .bind(departmentList.get(position))
    }

    override fun getItemCount(): Int {
        return departmentList.size
    }
}