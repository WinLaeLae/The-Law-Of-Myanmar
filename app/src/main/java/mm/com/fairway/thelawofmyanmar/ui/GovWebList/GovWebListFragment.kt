package mm.com.fairway.thelawofmyanmar.ui.GovWebList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_gov_web_list.*
import mm.com.fairway.thelawofmyanmar.R
import mm.com.fairway.thelawofmyanmar.adapter.DepartmentAdapter
import mm.com.fairway.thelawofmyanmar.viewModel.LawsDepartmentViewModel


class GovWebListFragment : Fragment(),DepartmentAdapter.ClickListener {
private lateinit var  departViewModel: LawsDepartmentViewModel
    private lateinit var departAdapter:DepartmentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gov_web_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        departViewModel.setLoadDepartments()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        departAdapter = DepartmentAdapter()
        departViewModel = ViewModelProvider(this).get(LawsDepartmentViewModel::class.java)
        recyclerView_GovWeb_list.apply {
            layoutManager= LinearLayoutManager(context)
            adapter=departAdapter
        }
        departAdapter.setOnClickListener(this)
        observeDepartment()
    }
    private  fun observeDepartment(){
        departViewModel.getLoadDepartments()
            .observe(viewLifecycleOwner, Observer<DepartmentListModel> { department ->
                departAdapter.updateDepartmentList(
                    department.departments as ArrayList<Department>
                )
                Log.d("govDepartment>>>>", department.departments.get(0).toString())
            })
    }

    override fun onClick(department: Department) {
       findNavController().navigate(GovWebListFragmentDirections.actionGovWebListFragmentToDetailGovWebFragment2(department.url))
    }
}