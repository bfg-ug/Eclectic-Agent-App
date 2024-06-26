package com.example.eclecticbank.view.dashboardScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.eclecticbank.model.Service
import com.example.eclecticbank.R
import com.example.eclecticbank.view.adapter.ServiceIconAdapter
import com.example.eclecticbank.databinding.FragmentCustomerEnquriesBinding


class CustomerEnquriesFragment : Fragment() {

    private var _binding: FragmentCustomerEnquriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var serviceIconAdapter: ServiceIconAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCustomerEnquriesBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //List of services
        val items = listOf(
            Service("Balance enquiry", R.drawable.balance_enquiry_icon,  R.id.homeDashboardFragment),
            Service("Mini-statement", R.drawable.mini_statement_icon, R.id.homeDashboardFragment),
            Service("Short Account Opening", R.drawable.short_account_opening_icon,  R.id.homeDashboardFragment),
            // Add more items as needed
        )

        //Adapter
        serviceIconAdapter = ServiceIconAdapter(items)


        // Recyclerview binding
        binding.recyclerview.apply {
            layoutManager = GridLayoutManager(requireContext(),3)
            adapter = serviceIconAdapter
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}