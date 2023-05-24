package com.berkayesen.afinal.viewmodel

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.berkayesen.afinal.databinding.FragmentMapBinding
import com.huawei.hmf.tasks.OnFailureListener
import com.huawei.hmf.tasks.OnSuccessListener
import com.huawei.hms.location.*
import com.huawei.hms.maps.*
import com.huawei.hms.maps.model.LatLng
import com.huawei.hms.maps.model.MarkerOptions
import kotlin.math.log


class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentMapBinding
    private var mMapView: MapView? = null
    private var hMap: HuaweiMap? = null
    private lateinit var settingsClient:SettingsClient
    private lateinit var mLocationRequest: LocationRequest
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    companion object{
        private const val TAG ="MapFragment"
    }
    val  requestMultiplePermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ){
        permissions ->
        permissions.entries.forEach{
            if(it.value){
                hMap?.isMyLocationEnabled = true
            }
            Log.d(TAG,"${it.key} = ${it.value}")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MapsInitializer.initialize(requireContext());
        binding = FragmentMapBinding.inflate(inflater,container,false)
        mMapView = binding.mapview
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle("MapViewBundleKey")
        }
        mMapView?.onCreate(mapViewBundle)
        mMapView?.getMapAsync(this)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestLocationPermission()
        settingsClient = LocationServices.getSettingsClient(requireActivity())
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

    }
    fun getFusedLocation(){
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.numUpdates = 1
        val mLocationCallback: LocationCallback
        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult != null) {
                    // TODO: Process the location callback result.
                }
            }
        }
        val lastLocation =
            fusedLocationProviderClient.lastLocation
        lastLocation.addOnSuccessListener(OnSuccessListener { location ->
            if (location == null) {
                //TODO:request update
                return@OnSuccessListener
            }
            val lat = location.latitude
            val long = location.longitude
            hMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat,long),16f))
            // TODO: Define logic for processing the Location object upon success.
            return@OnSuccessListener
        })
            // Define callback for failure in obtaining the last known location.
            .addOnFailureListener {
                // TODO: Define callback for API call failure.
            }
    }
    fun checkLocationSettings(){
        val builder = LocationSettingsRequest.Builder()
        mLocationRequest = LocationRequest()
        builder.addLocationRequest(mLocationRequest)
        val locationSettingsRequest = builder.build()
// Check the device location settings.
        settingsClient.checkLocationSettings(locationSettingsRequest)
            // Define the listener for success in calling the API for checking device location settings.
            .addOnSuccessListener(OnSuccessListener { locationSettingsResponse ->
                val locationSettingsStates = locationSettingsResponse.locationSettingsStates
                val stringBuilder = StringBuilder()
                // Check whether the location function is enabled.
                stringBuilder.append("isLocationUsable=")
                    .append(locationSettingsStates.isLocationUsable)
                // Check whether HMS Core (APK) is available.
                stringBuilder.append(",\nisHMSLocationUsable=")
                    .append(locationSettingsStates.isHMSLocationUsable)
                Log.i(TAG, "checkLocationSetting onComplete:$stringBuilder")
            })
            // Define callback for failure in checking the device location settings.
            .addOnFailureListener(OnFailureListener { e ->
                Log.i(TAG, "checkLocationSetting onFailure:" + e.message)
            })
    }
    fun requestLocationPermission(){
        if (!hasLocationPermission()){
            requestMultiplePermission.launch(arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ))
        }

    }

    private fun hasLocationPermission(): Boolean{
        return ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
    override fun onMapReady(p0: HuaweiMap?) {
        hMap = p0
        hMap?.mapType = HuaweiMap.MAP_TYPE_TERRAIN
        if(hasLocationPermission()){
            hMap?.isMyLocationEnabled = true
        }
        val options = MarkerOptions()
            .position(LatLng(38.630554, 27.422222))
            .title("Hello Huawei Map")
            .snippet("This is a snippet!")
        hMap?.addMarker(options)

        hMap?.setOnMarkerClickListener { marker ->
            val position = marker.position.toString()
            Log.i(TAG,"onMarkerClick:${marker.position}")
            false
        }
        if(hasLocationPermission()){
            getFusedLocation()
        }
    }

    /*private fun addMarker(chargerList :List<LatLng>){
        chargerList.forEach{
            val options = MarkerOptions()
                .position(it)
            //.title("Hello Huawei Map")
            //.snippet("This is a snippet!")
            hMap?.addMarker(options)
        }

    }*/
    override fun onStart() {
        super.onStart()
        mMapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mMapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mMapView?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView?.onLowMemory()
    }


}