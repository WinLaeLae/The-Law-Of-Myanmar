package mm.com.fairway.thelawofmyanmar.ui.townshipCourt

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_detail_tsp_court.*
import mm.com.fairway.thelawofmyanmar.R
import mm.com.fairway.thelawofmyanmar.viewModel.LawsDepartmentViewModel
import java.io.IOException

class DetailTspCourtFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var map: GoogleMap
    private var courtID: Int = 0
    private var lat: Double = 0.0
    private var lng: Double = 0.0
    private var title = ""
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var courtViewModel: LawsDepartmentViewModel

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val PLACE_PICKET_REQUEST = 3
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_detail_tsp_court, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        // Inflate the layout for this fragment


        return view
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
        val location = title
        var addressList: List<Address>? = null
        if (location != ""){
            val geocoder = Geocoder(requireContext())
            try {
                addressList = geocoder.getFromLocationName(location,5)
            }catch (e: IOException){
                e.printStackTrace()
            }
            if (addressList!!.isEmpty()){
                for (i in addressList !!. indices){
                    val address = addressList[i]
                    val latLng= LatLng(lat,lng)
                    placeMarkerOnMap(latLng)
                    map.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                }
            }
        }

    }

    private fun observerDetaiCourt() {
        courtViewModel.getDetailCourt()
            .observe(viewLifecycleOwner,
                Observer<DetaiCourtModel> { court ->
                    Log.d("detailCourt", court.toString())
                    lat = court.court.lat
                    lng = court.court.lng
                    title = court.court.name
                    tsp_courtName_txt.text = court.court.name
                    tsp_address_txt.text = court.court.address
                    phone_txt.text = court.court.phone

                })
    }


    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap!!

        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener(this)
        setUpMap()
    }


    override fun onMarkerClick(p0: Marker?) = false
    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        map.isMyLocationEnabled = true
        map.mapType = GoogleMap.MAP_TYPE_TERRAIN
        fusedLocationProviderClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->
            if (location != null) {
                //  val currentLatLng = LatLng(lat, lng)
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
            }

        }
    }

    private fun getAddress(latLng: LatLng): String {
        val context = this
        val lat = this.lat
        val lon = this.lng
        var addresses = mutableListOf<Address>()
        var errorMessage = ""
        var address: String? = null
        try {
            addresses = Geocoder(requireContext()).getFromLocation(lat, lon, 1)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        } catch (illegalArgumentException: IllegalAccessException) {
            illegalArgumentException.printStackTrace()
        }
        if (addresses.isEmpty()) {
            if (errorMessage.isEmpty()) {

            }
        } else {
            val addressItem = addresses.first()
            val addressFragment = (0..addressItem.maxAddressLineIndex).map { i ->
                addressItem.getAddressLine(i)
            }
            address = addressFragment.first()

        }
        return address ?: errorMessage
    }

    private fun placeMarkerOnMap(location: LatLng) {
        val markerOptions = MarkerOptions().position(location)
        val titleStr = getAddress(location)
        markerOptions.title(titleStr)
        map.addMarker(markerOptions)
    }

}