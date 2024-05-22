package com.example.eclecticbank.Views.AdditionalScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.eclecticbank.R
import com.example.eclecticbank.databinding.CashWithdrawalBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottonsheetFragement : BottomSheetDialogFragment() {

    private var _binding: CashWithdrawalBottomSheetBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  CashWithdrawalBottomSheetBinding.inflate(inflater, container, false)


        binding.closeButton.setOnClickListener{
            dismiss()
        }

        binding.cardlessWithdrawalCard.setOnClickListener{
            findNavController().navigate(R.id.action_homeDashboardFragment_to_cardlessWithdrawal)
            dismiss()
        }

        binding.mobileMoneyWithdrawalCard.setOnClickListener{
            findNavController().navigate(R.id.action_homeDashboardFragment_to_mobileMoneyWithdrawal)
            dismiss()
        }

        dialog?.setCanceledOnTouchOutside(false)
        dialog?.setCancelable(false)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): BottomSheetDialogFragment {
            return BottonsheetFragement()
        }
    }

}