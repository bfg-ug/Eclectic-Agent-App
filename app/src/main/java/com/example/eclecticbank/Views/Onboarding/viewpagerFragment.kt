package com.example.eclecticbank.Views.Onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eclecticbank.R
import com.example.eclecticbank.databinding.FragmentOnBoardingScreen1Binding
import com.example.eclecticbank.databinding.FragmentViewpagerBinding

class viewpagerFragment : Fragment() {

    private var _binding: FragmentViewpagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentViewpagerBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            onBoardingScreen1(),
            onBoardingScreen2(),
            onBoardingScreeen3(),
            onBoardingScreen4()
        )

        val adapter = ViewPagerAdapter(fragmentList, getChildFragmentManager(), lifecycle)

        binding.viewPager.adapter = adapter






        return binding.root
    }
}