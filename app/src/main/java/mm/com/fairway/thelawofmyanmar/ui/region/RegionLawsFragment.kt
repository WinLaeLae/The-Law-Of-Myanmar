package mm.com.fairway.thelawofmyanmar.ui.region

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_region_laws.*
import mm.com.fairway.thelawofmyanmar.R
import mm.com.fairway.thelawofmyanmar.adapter.RegionAdapter
import mm.com.fairway.thelawofmyanmar.viewModel.LawsDepartmentViewModel

class RegionLawsFragment : Fragment(),RegionAdapter.ClickListener {
    private lateinit var regionViewModel: LawsDepartmentViewModel
    private lateinit var regionAdapter: RegionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_region_laws, container, false)
    }

    override fun onResume() {
        super.onResume()
        regionViewModel.setRegionList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        regionViewModel = ViewModelProvider(this).get(LawsDepartmentViewModel::class.java)
        regionAdapter = RegionAdapter()
        recyclerView_region_departmentName.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = regionAdapter
        }
        regionAdapter.setOnClickListener(this)
        oserverRegion()
    }
    fun oserverRegion(){
        regionViewModel.getRegion().observe(viewLifecycleOwner, Observer<RegionsListModel> {
            region ->
            regionAdapter.updateRegionList(
                region.regions as ArrayList<Region>
            )

        })
    }

    override fun onClick(region: Region) {
        findNavController().navigate(RegionLawsFragmentDirections.actionRegionLawsFragmentToLawsListFragment())
    }
}