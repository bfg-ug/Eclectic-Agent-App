package com.example.eclecticbank.Views.AdditionalScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eclecticbank.Models.MobileMoneyRecyclerViewAdapter
import com.example.eclecticbank.Models.MobileMoneyProvider
import com.example.eclecticbank.R
import com.example.eclecticbank.databinding.FragmentMobileMoneyWithdrawalBinding


class MobileMoneyWithdrawal : Fragment() {

    private var _binding: FragmentMobileMoneyWithdrawalBinding? = null
    private val binding get() = _binding!!
    private lateinit var mobileMoneyRecyclerViewAdapter: MobileMoneyRecyclerViewAdapter

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

        //Back Arrow
        binding.toolbar.setOnClickListener{
            findNavController().navigate(R.id.action_mobileMoneyWithdrawal_to_homeDashboardFragment)
        }

        // Mobile Money service provider adapter
        mobileMoneyRecyclerViewAdapter = MobileMoneyRecyclerViewAdapter(items)

        //recyclerview viewbinding
        binding.mobileMoneyRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = mobileMoneyRecyclerViewAdapter
        }






        return binding.root
    }

}