package com.berkayesen.afinal

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.berkayesen.afinal.databinding.FragmentSearchBinding
import com.berkayesen.afinal.retrofit2.data.model.ChargeJsonItem
import com.hbb20.CountryCodePicker
import com.huawei.agconnect.core.service.auth.AuthProvider
import com.huawei.hmf.tasks.OnSuccessListener
import com.huawei.hms.location.*
import com.huawei.hms.maps.CameraUpdateFactory
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.MapsInitializer
import com.huawei.hms.maps.model.LatLng
import com.huawei.hms.support.account.result.AuthAccount


class SearchFragment : Fragment() {

    private lateinit var binding : FragmentSearchBinding
    private var mMapView: MapView? = null
    private var hMap: HuaweiMap? = null
    private lateinit var settingsClient: SettingsClient
    private lateinit var mLocationRequest: LocationRequest
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var chargeJsonItemList : ArrayList<ChargeJsonItem> ?= null

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
            Log.d(SearchFragment.TAG,"${it.key} = ${it.value}")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapsInitializer.setApiKey("DAEDAGAYHMojfxUfAXdc0HeLrxnYzrjhsUpm6zjonvRMJy2kbH76UPkMfK77nInn41Ybcya6NJ/USIiTiqVdzsK4j8tFNsBQQORpPw==")
        requestLocationPermission()
        settingsClient = LocationServices.getSettingsClient(requireActivity())
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val receivedData = arguments?.getString("username")
        //binding.textView2.text = receivedData
        getFusedLocation()
        binding.button2.setOnClickListener{
            findNavController().navigate(R.id.action_searchFragment_to_mapFragment)
        }



    }
    @SuppressLint("SetTextI18n")
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
            binding.imageButton.setOnClickListener{
                binding.textView10.text = lat.toString()
                binding.textView11.text= long.toString()
            }
            binding.textView12.setOnClickListener{
                binding.textView10.text = "0.000"
                binding.textView11.text= "0.000"
            }


            Log.d(SearchFragment.TAG,"latitude :::: ${lat}")
            hMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat,long),7f))
            // TODO: Define logic for processing the Location object upon success.
            return@OnSuccessListener
        })
            // Define callback for failure in obtaining the last known location.
            .addOnFailureListener {
                // TODO: Define callback for API call failure.
            }

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
}