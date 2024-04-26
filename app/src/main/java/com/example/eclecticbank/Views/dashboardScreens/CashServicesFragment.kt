package com.example.eclecticbank.Views.dashboardScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.eclecticbank.R
import com.example.eclecticbank.databinding.FragmentCashServicesBinding


class CashServicesFragment : Fragment() {

    private var _binding: FragmentCashServicesBinding? = null
    private val binding get() = _binding!!
    private lateinit var serviceIconAdapter: ServiceIconAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCashServicesBinding.inflate(inflater, container, false)



        //list of services on the screen
        val items = listOf(
            Service("Cash withdrawal", R.drawable.cash_withdrawal_icon,  R.id.homeDashboardFragment),
            Service("Cash Deposit", R.drawable.cash_deposit_icon, R.id.action_homeDashboardFragment_to_cashDepositFragment),
            Service("Cash Collection", R.drawable.cash_collection_icon,  R.id.homeDashboardFragment),
            Service("Payments", R.drawable.cash_withdrawal_icon,  R.id.homeDashboardFragment),
            Service("Funds Transfer", R.drawable.cash_deposit_icon,  R.id.homeDashboardFragment),
            Service("Remittance", R.drawable.cash_collection_icon,  R.id.homeDashboardFragment),
            Service("Top-Up Service", R.drawable.cash_withdrawal_icon,  R.id.homeDashboardFragment),
            // Add more items as needed
        )



        //Services adapter
        serviceIconAdapter = ServiceIconAdapter(items)

        //recyclerview viewbinding
        binding.recyclerview.apply {
            layoutManager = GridLayoutManager(requireContext(),3)
            adapter = serviceIconAdapter
        }

        //return view
        return binding.root
    }



}