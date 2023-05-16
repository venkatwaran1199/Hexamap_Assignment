package com.example.hexamap_assignment.ui.fragment

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.hexamap_assignment.R
import com.example.hexamap_assignment.databinding.FragmentSuccessmapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class successmaps : Fragment(),OnMapReadyCallback {

    private var Sbinding:FragmentSuccessmapsBinding? = null
    private val binding get() = Sbinding!!
    lateinit var map:GoogleMap
    val args:successmapsArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Sbinding =  FragmentSuccessmapsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(gmap: GoogleMap) {
        map = gmap
        val MarkerOptions = MarkerOptions()
            .position(LatLng(args.addeddata!!.latitude,args.addeddata!!.longitude))
            .visible(true)
            .anchor(0.5f, 1f)
        map.addMarker(MarkerOptions)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(args.addeddata!!.latitude,args.addeddata!!.longitude),18f))
    }
}