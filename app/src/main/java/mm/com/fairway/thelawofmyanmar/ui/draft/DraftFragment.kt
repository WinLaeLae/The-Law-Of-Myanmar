package mm.com.fairway.thelawofmyanmar.ui.draft

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_draft.*
import mm.com.fairway.thelawofmyanmar.R
import mm.com.fairway.thelawofmyanmar.adapter.LawsAdapter
import mm.com.fairway.thelawofmyanmar.ui.GovWebList.Department
import mm.com.fairway.thelawofmyanmar.ui.GovWebList.DepartmentListModel
import mm.com.fairway.thelawofmyanmar.viewModel.LawsDepartmentViewModel

private lateinit var  departViewModel:LawsDepartmentViewModel
private lateinit var lawAdapter:LawsAdapter
class DraftFragment : Fragment() ,LawsAdapter.ClickListener{



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_draft, container, false)

    }

    override fun onResume() {
        super.onResume()
        departViewModel.setLoadDepartments()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lawAdapter = LawsAdapter()
        departViewModel= ViewModelProvider(this).get(LawsDepartmentViewModel::class.java)
        recyclerView_Draft_departmentName.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = lawAdapter
        }
        lawAdapter.setOnClickListener(this)
        observerDraftDepartment()
    }

    private fun observerDraftDepartment(){
        departViewModel.getLoadDepartments()
            .observe(viewLifecycleOwner, Observer<DepartmentListModel> { department ->
                lawAdapter.updateDepartmentList(
                    department.departments as ArrayList<Department>
                )
            })
    }
    override fun OnClick(department: Department) {
        findNavController().navigate(DraftFragmentDirections.actionNavDraftsToLawsListFragment())
    }
}