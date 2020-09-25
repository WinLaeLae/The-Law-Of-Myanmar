package mm.com.fairway.thelawofmyanmar.ui.townshipCourt

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_township_court_office.*
import mm.com.fairway.thelawofmyanmar.R
import mm.com.fairway.thelawofmyanmar.adapter.TspCourtAdapter
import mm.com.fairway.thelawofmyanmar.viewModel.LawsDepartmentViewModel

class TownshipCourtOfficeFragment : Fragment(), TspCourtAdapter.ClickListener {
    private lateinit var courtViewModel: LawsDepartmentViewModel
    private lateinit var courtAdapter: TspCourtAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_township_court_office, container, false)
    }

    override fun onResume() {
        super.onResume()
        courtViewModel.setLoadCourts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courtViewModel = ViewModelProvider(this).get(LawsDepartmentViewModel::class.java)
        courtAdapter = TspCourtAdapter()
        recyclerView_TspCourtOffice.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = courtAdapter
        }
        courtAdapter.setOnClickListener(this)
        observerCourt()
    }

    fun observerCourt() {
        courtViewModel.getCourtsList().observe(viewLifecycleOwner, Observer { courts ->
            courtAdapter.updateTspCourtList(
                courts.courts as ArrayList<Court>
            )
        })

    }

    override fun onClick(court: Court) {
        findNavController().navigate(
            TownshipCourtOfficeFragmentDirections.actionNavTownshipCourtToDetailTspCourtFragment(
                court.id
            )
        )
    }

}