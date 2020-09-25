package mm.com.fairway.thelawofmyanmar.ui.townshipCourt

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_detail_tsp_court.*
import mm.com.fairway.thelawofmyanmar.R
import mm.com.fairway.thelawofmyanmar.viewModel.LawsDepartmentViewModel

class DetailTspCourtFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var map: GoogleMap
    private var courtID: Int = 0
    private var lat: Double = 0.0
    private var lng: Double = 0.0
    private var title = ""
    private lateinit var courtViewModel: LawsDepartmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_tsp_court, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var dataItem = arguments?.let {
            DetailTspCourtFragmentArgs.fromBundle(it)
        }
        courtID = dataItem!!.courtID
        Log.d("Id>>", courtID.toString())
        courtViewModel = ViewModelProvider(this).get(LawsDepartmentViewModel::class.java)
        courtViewModel.setDetailCourt(courtID)
        observerDetaiCourt()
    }

    private fun observerDetaiCourt() {
        courtViewModel.getDetailCourt()
            .observe(viewLifecycleOwner,
                Observer<Court> { court ->
                    lat = court.lat
                    lng = court.lng
                    title = court.name
                    tsp_courtName_txt.text = court.name
                    tsp_address_txt.text = court.address
                    phone_txt.text = court.phone
                    mapView.getMapAsync(this)
                })
    }


    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap!!
        val currentLatLng = LatLng(lat, lng)
        val options = GoogleMapOptions()
        googleMap?.addMarker(
            MarkerOptions().position(LatLng(lat, lng)).title(title)
        )
        options.mapType(GoogleMap.MAP_TYPE_TERRAIN).compassEnabled(false)
            .rotateGesturesEnabled(false)
            .tiltGesturesEnabled(false)
        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener(this)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
    }

    override fun onMarkerClick(p0: Marker?) = false

}