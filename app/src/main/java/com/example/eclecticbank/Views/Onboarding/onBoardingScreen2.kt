package com.example.eclecticbank.Views.Onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.eclecticbank.R
import com.example.eclecticbank.databinding.FragmentOnBoardingScreen1Binding
import com.example.eclecticbank.databinding.FragmentOnboardingScreen2Binding


class onBoardingScreen2 : Fragment() {


    private var _binding: FragmentOnboardingScreen2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentOnboardingScreen2Binding.inflate(inflater, container, false)

        val viewpager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        //next arrow
        binding.nextArrow.setOnClickListener(){
            viewpager?.currentItem = viewpager?.currentItem!! + 1
        }


        //Skip button
        binding.skiptext.setOnClickListener(){
            viewpager?.currentItem = 3
        }



        return binding.root
    }

}