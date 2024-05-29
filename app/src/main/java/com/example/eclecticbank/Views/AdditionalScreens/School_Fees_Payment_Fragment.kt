package com.example.eclecticbank.Views.AdditionalScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eclecticbank.Models.BottomSheetOption
import com.example.eclecticbank.R
import com.example.eclecticbank.databinding.FragmentSchoolFeesBinding
import com.example.eclecticbank.databinding.FragmentSchoolFeesPaymentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class School_Fees_Payment_Fragment(private  val schoolName: String) : Fragment() {

    private var _binding: FragmentSchoolFeesPaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.schoolTitle.setText(schoolName)


        // Inflate the layout for this fragment
        _binding = FragmentSchoolFeesPaymentBinding.inflate(inflater, container, false)

        return binding.root
    }



    companion object {
        fun newInstance(schoolTitleText: String): School_Fees_Payment_Fragment {
            return School_Fees_Payment_Fragment(schoolTitleText)
        }
    }

}