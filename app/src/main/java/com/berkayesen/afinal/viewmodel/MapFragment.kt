package com.berkayesen.afinal.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.berkayesen.afinal.databinding.FragmentMapBinding
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.MapsInitializer
import com.huawei.hms.maps.OnMapReadyCallback


class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentMapBinding
    private var mMapView: MapView? = null
    private var hMap: HuaweiMap? = null
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

    override fun onMapReady(p0: HuaweiMap?) {
        hMap = p0
    }
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