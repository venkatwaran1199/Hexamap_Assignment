package com.example.hexamap_assignment.ui.fragment.maps

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hexamap_assignment.R
import com.example.hexamap_assignment.databinding.FragmentMapsBinding
import com.example.hexamap_assignment.models.utils.Permissions.hasLocationPermission
import com.example.hexamap_assignment.models.utils.Permissions.requestLocationPermission
import com.example.hexamap_assignment.viewmodels.AddViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog

class MapsFragment : Fragment(),OnMapReadyCallback,EasyPermissions.PermissionCallbacks {

    private var Mbinding:FragmentMapsBinding? = null
    private val binding get() = Mbinding!!
    private val AddViewModel: AddViewModel by viewModels()
    var builder = LatLngBounds.Builder()

    private lateinit var map :GoogleMap
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    var locationlist = mutableListOf<LatLng>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Mbinding =  FragmentMapsBinding.inflate(inflater, container, false)
        AddViewModel.getAllData.observe(viewLifecycleOwner) {
            if(it.isEmpty()){

            }else{
                for(i in it){
                    locationlist.add(LatLng(i.latitude, i.longitude))
                }
                showmarkers()
            }

        }


        binding.FABAddData.setOnClickListener {
            findNavController().navigate(R.id.action_mapsFragment_to_add2)
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        return binding.root
    }

    private fun showmarkers() {
        for (i in locationlist.indices) {
            // below line is use to add marker to each location of our array list.
            val MarkerOptions = MarkerOptions()
                .position(locationlist.get(i))
                .visible(true)
                .anchor(0.5f, 1f)
            map.addMarker(MarkerOptions)
            builder.include(MarkerOptions.position)
            // below line is use to move our camera to the specific location.
        }
        val bounds: LatLngBounds = builder.build()
        val padding = 200 // offset from edges of the map in pixels

        val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
        map.animateCamera(cu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googlemap: GoogleMap) {
        map = googlemap
        map.uiSettings.isZoomControlsEnabled = false
        map.uiSettings.isCompassEnabled = true
        map.uiSettings.isMyLocationButtonEnabled = true



        if(hasLocationPermission(requireContext())){
            map.isMyLocationEnabled = true
            /*mFusedLocationClient.lastLocation.addOnSuccessListener {
                val Location = LatLng(it.latitude,it.longitude)
                val cameraPosition = CameraPosition.Builder().target(Location).zoom(18f).build()
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            }*/
        }else{
            requestLocationPermission(this)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        EasyPermissions.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults,
            this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            SettingsDialog.Builder(requireActivity()).build().show()
        }else{
            requestLocationPermission(this)
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        map.uiSettings.isMyLocationButtonEnabled = true
    }
}