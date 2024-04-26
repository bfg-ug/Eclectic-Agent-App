package com.example.eclecticbank.Views.dashboardScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.eclecticbank.R
import com.example.eclecticbank.databinding.FragmentCustomerEnquriesBinding


class CustomerEnquriesFragment : Fragment() {

    private var _binding: FragmentCustomerEnquriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var serviceIconAdapter: ServiceIconAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCustomerEnquriesBinding.inflate(inflater, container, false)



        //List of services
        val items = listOf(
            Service("Balance enquiry", R.drawable.cash_withdrawal_icon,  R.id.homeDashboardFragment),
            Service("Mini-statement", R.drawable.cash_deposit_icon, R.id.homeDashboardFragment),
            Service("Short Account Opening", R.drawable.cash_collection_icon,  R.id.homeDashboardFragment),
            // Add more items as needed
        )

        //Adapter
        serviceIconAdapter = ServiceIconAdapter(items)


        // Recyclerview binding
        binding.recyclerview.apply {
            layoutManager = GridLayoutManager(requireContext(),3)
            adapter = serviceIconAdapter
        }



        return binding.root
    }

}