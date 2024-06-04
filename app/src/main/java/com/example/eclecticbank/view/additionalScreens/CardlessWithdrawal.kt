package com.example.eclecticbank.view.additionalScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.eclecticbank.R
import com.example.eclecticbank.databinding.FragmentCardlessWithdrawalBinding


class CardlessWithdrawal : Fragment() {

    private var _binding: FragmentCardlessWithdrawalBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentCardlessWithdrawalBinding.inflate(inflater, container, false)


        //Back arrow
        binding.toolbar.setOnClickListener{
            findNavController().navigate(R.id.action_cardlessWithdrawal_to_homeDashboardFragment)
        }



        return binding.root
    }

}