package com.example.eclecticbank.Views.Onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.eclecticbank.R
import com.example.eclecticbank.databinding.FragmentOnBoardingScreen4Binding


class onBoardingScreen4 : Fragment() {


    private var _binding:FragmentOnBoardingScreen4Binding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOnBoardingScreen4Binding.inflate(inflater, container, false)

        val viewpager = activity?.findViewById<ViewPager2>(R.id.viewPager)


        //Back to begin button
        binding.backtext.setOnClickListener(){
            viewpager?.currentItem = 0
        }

        //Get started button
        binding.getStartedText.setOnClickListener(){
            findNavController().navigate(R.id.action_viewpagerFragment_to_homeDashboardFragment)
            onBoardingComplete()

        }

        return binding.root
    }


    //Change shared preference to Complete
    private fun onBoardingComplete(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}