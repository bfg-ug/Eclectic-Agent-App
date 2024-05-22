package com.example.eclecticbank.Views.AdditionalScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eclecticbank.Models.MobileMoneyAdapter
import com.example.eclecticbank.Models.MobileMoneyProvider
import com.example.eclecticbank.R
import com.example.eclecticbank.Views.dashboardScreens.ServiceIconAdapter
import com.example.eclecticbank.databinding.FragmentCashServicesBinding
import com.example.eclecticbank.databinding.FragmentMobileMoneyWithdrawalBinding


class MobileMoneyWithdrawal : Fragment() {

    private var _binding: FragmentMobileMoneyWithdrawalBinding? = null
    private val binding get() = _binding!!
    private lateinit var mobileMoneyAdapter: MobileMoneyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMobileMoneyWithdrawalBinding.inflate(inflater, container, false)


        // List of mobile money Providers
        val items = listOf(
            MobileMoneyProvider("Mpesa", R.drawable.mpesa),
            MobileMoneyProvider("Airtel Money", R.drawable.airtel),
            MobileMoneyProvider("MTN", R.drawable.mask),
            )

        // Mobile Money service provider adapter
        mobileMoneyAdapter = MobileMoneyAdapter(items)

        //recyclerview viewbinding
        binding.mobileMoneyRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = mobileMoneyAdapter
        }

        //Back Arrow
        binding.toolbar.setOnClickListener{
            findNavController().navigate(R.id.action_mobileMoneyWithdrawal_to_homeDashboardFragment)
        }




        return binding.root
    }

}