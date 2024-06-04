package com.example.eclecticbank.view.onboardingScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eclecticbank.view.adapter.OnboardingViewPagerAdapter
import com.example.eclecticbank.databinding.FragmentViewpagerBinding

class ViewpagerFragment : Fragment() {

    private var _binding: FragmentViewpagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentViewpagerBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            OnBoardingScreen1(),
            OnBoardingScreen2(),
            OnBoardingScreen3(),
            OnBoardingScreen4()
        )

        val adapter = OnboardingViewPagerAdapter(fragmentList, getChildFragmentManager(), lifecycle)

        binding.viewPager.adapter = adapter






        return binding.root
    }
}