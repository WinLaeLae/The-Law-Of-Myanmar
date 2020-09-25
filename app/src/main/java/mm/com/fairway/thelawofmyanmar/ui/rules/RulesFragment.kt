package mm.com.fairway.thelawofmyanmar.ui.rules

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_rule.*
import mm.com.fairway.thelawofmyanmar.R
import mm.com.fairway.thelawofmyanmar.adapter.LawsAdapter
import mm.com.fairway.thelawofmyanmar.ui.GovWebList.Department
import mm.com.fairway.thelawofmyanmar.ui.GovWebList.DepartmentListModel
import mm.com.fairway.thelawofmyanmar.viewModel.LawsDepartmentViewModel


class RulesFragment : Fragment(), LawsAdapter.ClickListener {
    private lateinit var lawsDepartViewModel: LawsDepartmentViewModel
    private lateinit var lawAdapter: LawsAdapter


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.fragment_rule, container, false)
    }
    override fun onResume() {
        super.onResume()
        lawsDepartViewModel.setLoadDepartments();

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lawAdapter= LawsAdapter()
        lawsDepartViewModel = ViewModelProvider(this).get(LawsDepartmentViewModel::class.java)
        recyclerView_Rule_departmentName.apply {
            layoutManager = LinearLayoutManager(context);
            adapter = lawAdapter
        }
        lawAdapter.setOnClickListener(this)
        observeLawDepartment()
    }
    private  fun observeLawDepartment(){
        lawsDepartViewModel.getLoadDepartments()
            .observe(viewLifecycleOwner, Observer<DepartmentListModel> { department ->
                lawAdapter.updateDepartmentList(
                    department.departments as ArrayList<Department>
                )
                Log.d("LawDapart3>>>>", department.departments.get(0).toString())
            })
    }

    override fun OnClick(department: Department) {
        findNavController().navigate(RulesFragmentDirections.actionNavRulesToLawsListFragment())
    }
}