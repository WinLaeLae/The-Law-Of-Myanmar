package mm.com.fairway.thelawofmyanmar.ui.Laws

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_laws.*
import mm.com.fairway.thelawofmyanmar.R
import mm.com.fairway.thelawofmyanmar.adapter.LawsAdapter
import mm.com.fairway.thelawofmyanmar.ui.GovWebList.Department
import mm.com.fairway.thelawofmyanmar.ui.GovWebList.DepartmentListModel
import mm.com.fairway.thelawofmyanmar.viewModel.LawsDepartmentViewModel

class LawsFragment : Fragment() , LawsAdapter.ClickListener{
    private lateinit var lawsDepartmentViewModel: LawsDepartmentViewModel
    private lateinit var lawAdapter: LawsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_laws, container, false)
    }

    override fun onResume() {
        super.onResume()
        lawsDepartmentViewModel.setLoadDepartments();

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lawAdapter= LawsAdapter()
        lawsDepartmentViewModel = ViewModelProvider(this).get(LawsDepartmentViewModel::class.java)
       recyclerView_departmentName.apply {
           layoutManager = LinearLayoutManager(context);
           adapter = lawAdapter
       }
        lawAdapter.setOnClickListener(this)
        observeLawDepartment()
    }

    override fun OnClick(department: Department) {
findNavController().navigate(LawsFragmentDirections.actionNavLawsToLawsListFragment())
    }
    private  fun observeLawDepartment(){
        lawsDepartmentViewModel.getLoadDepartments()
            .observe(viewLifecycleOwner, Observer<DepartmentListModel> { department ->
              lawAdapter.updateDepartmentList(
                  department.departments as ArrayList<Department>
              )
                Log.d("LawDapart2>>>>", department.departments.get(0).toString())
            })
    }
}