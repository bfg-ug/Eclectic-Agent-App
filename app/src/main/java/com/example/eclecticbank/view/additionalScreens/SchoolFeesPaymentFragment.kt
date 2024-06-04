package com.example.eclecticbank.view.additionalScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.eclecticbank.model.Schools
import com.example.eclecticbank.R
import com.example.eclecticbank.databinding.FragmentSchoolFeesPaymentBinding

class SchoolFeesPaymentFragment() : Fragment() {

    private var _binding: FragmentSchoolFeesPaymentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        _binding = FragmentSchoolFeesPaymentBinding.inflate(inflater, container, false)


        binding.toolbar.setOnClickListener{
            findNavController().navigate(R.id.action_school_Fees_Payment_Fragment_to_school_fees_Fragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val schoolName = arguments?.getString("schoolName")
        val schoolType = arguments?.getString("schoolType")
        val schoolLocation = arguments?.getString("schoolLocation")

        binding.schoolTitle.text = schoolName
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    companion object {
        fun newInstance(schools: Schools): SchoolFeesPaymentFragment {
            return SchoolFeesPaymentFragment()
        }
    }

}