package com.example.eclecticbank.Views.dashboardScreens


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eclecticbank.Views.Adapters.tabviewpagerAdapter
import com.example.eclecticbank.databinding.FragmentHomeDashboardBinding
import com.google.android.material.tabs.TabLayoutMediator

class homeDashboardFragment : Fragment() {

    private var _binding: FragmentHomeDashboardBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeDashboardBinding.inflate(inflater, container, false)

        // List of fragment screens
        val fragmentList = arrayListOf<Fragment>(
            CashServicesFragment(),
            AgentServicesFragment(),
            CustomerEnquriesFragment()
        )


        // List of tab title
        val titleList = arrayListOf<String>(
            "Cash Service",
            "Agent Services",
            "Customer Enquries"
        )


        // viewpager adapter
        val adapter = tabviewpagerAdapter(fragmentList,requireActivity())

        binding.tabViewpager.adapter = adapter


        //Tab layout binding
        TabLayoutMediator(binding.tabLayout, binding.tabViewpager) { tab, position ->
            tab.text = titleList[position]
        }.attach()




        return binding.root
    }


}


