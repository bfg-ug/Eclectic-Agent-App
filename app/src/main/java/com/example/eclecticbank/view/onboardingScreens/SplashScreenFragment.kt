package com.example.eclecticbank.view.onboardingScreens

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.eclecticbank.R
class SplashScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler().postDelayed({

            //if completed onboarding send to dashboard
            if (onBoardingComplete()){
                findNavController().navigate(R.id.action_splashScreenFragment2_to_homeDashboardFragment)
            }
            //if not completed onboarding send to onboarding screens
            else{
                findNavController().navigate(R.id.action_splashScreenFragment2_to_viewpagerFragment)
            }

        },1500)

        //Display splash screen
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }
        //Set on completed shard preference to false on first install
        private fun onBoardingComplete():Boolean{
            val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)

            return sharedPref.getBoolean("Finished", false)

        }


}