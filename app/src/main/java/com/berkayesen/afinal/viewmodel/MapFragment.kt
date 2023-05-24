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
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.MapsInitializer
import com.huawei.hms.maps.OnMapReadyCallback
import com.huawei.hms.maps.model.LatLng
import com.huawei.hms.maps.model.MarkerOptions
import kotlin.math.log


class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentMapBinding
    private var mMapView: MapView? = null
    private var hMap: HuaweiMap? = null
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