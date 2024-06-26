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
import com.example.eclecticbank.databinding.FragmentAgentServicesBinding


class AgentServicesFragment : Fragment() {

    private var _binding: FragmentAgentServicesBinding? = null
    private val binding get() = _binding!!
    private lateinit var serviceIconAdapter: ServiceIconAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAgentServicesBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // List of services
        val items = listOf(
            Service("Agent Float", R.drawable.agent_float_icon, R.id.homeDashboardFragment),
            Service("Transaction status", R.drawable.transaction_status_icon,  R.id.homeDashboardFragment),
            Service("Summary report", R.drawable.summary_report_icon,  R.id.homeDashboardFragment),
            Service("Lead generation", R.drawable.lead_generation_icon,  R.id.homeDashboardFragment),
            // Add more items as needed
        )

        //Service icon adapter
        serviceIconAdapter = ServiceIconAdapter(items)

        //Recyclerview binding
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