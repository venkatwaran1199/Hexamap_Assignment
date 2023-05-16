package com.example.hexamap_assignment.ui.fragment.welcome

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.hexamap_assignment.R
import com.example.hexamap_assignment.databinding.FragmentWelcomeBinding


class Welcome : Fragment() {

    private var Wbinding:FragmentWelcomeBinding? = null
    private val binding get() = Wbinding!!
    lateinit var fadein:Animation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        Wbinding =  FragmentWelcomeBinding.inflate(inflater, container, false)
        fadein = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_in)
            //fade in animation
           // binding.imagesplash.animation = fadein

        Handler().postDelayed({
            findNavController().navigate(R.id.action_welcome_to_mapsFragment)
        },3000)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Wbinding = null
    }

}