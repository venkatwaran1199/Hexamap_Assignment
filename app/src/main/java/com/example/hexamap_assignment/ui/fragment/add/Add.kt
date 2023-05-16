package com.example.hexamap_assignment.ui.fragment.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hexamap_assignment.databinding.FragmentAddBinding
import com.example.hexamap_assignment.models.data.Add_Data
import com.example.hexamap_assignment.viewmodels.AddViewModel

class add : Fragment() {

    private var Abinding:FragmentAddBinding? = null
    private val binding get() = Abinding!!
    private val AddViewModel: AddViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Abinding =  FragmentAddBinding.inflate(inflater, container, false)

        binding.btnSubmit.setOnClickListener {
            submitdata()
        }

        return binding.root
    }


    private fun submitdata() {
        val name = binding.edtName.text.toString()
        val email = binding.edtemail.text.toString()
        val phone = binding.edtphone.text.toString()
        val address = binding.edtAddress.text.toString()
        val DOB = binding.edtDOB.text.toString()
        val NumberOfPersons = binding.edtNoOfPersons.text.toString()
        val latitude = binding.edtlatitude.text.toString()
        val longitude = binding.edtlongitude.text.toString()

        val validation = AddViewModel.validateuser(name,email,phone,address,DOB,NumberOfPersons,latitude,longitude)

        if(validation){
            val addeddata = Add_Data(0,name,email,phone,address,DOB,NumberOfPersons.toInt(),latitude.toDouble(),longitude.toDouble())
            AddViewModel.insertData(addeddata)
            Toast.makeText(requireContext(),"successfully Added",Toast.LENGTH_SHORT).show()
            val action = addDirections.actionAdd2ToSuccessmaps(addeddata)
            findNavController().navigate(action)
        }else{
            Toast.makeText(requireContext(),"Please fill all the Fields",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Abinding = null
    }

}