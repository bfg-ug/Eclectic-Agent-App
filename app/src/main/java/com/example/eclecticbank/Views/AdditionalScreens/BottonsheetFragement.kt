package com.example.eclecticbank.Views.AdditionalScreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eclecticbank.Models.BottomSheetOption
import com.example.eclecticbank.R
import com.example.eclecticbank.Views.AdditionalScreens.RectangularWidgetRecyclerViewAdapter
import com.example.eclecticbank.databinding.CashWithdrawalBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottonsheetFragement(private val items: List<BottomSheetOption>, private val bottomsheetTitleText: String) : BottomSheetDialogFragment() {

    private var _binding: CashWithdrawalBottomSheetBinding? = null
    private val binding get() = _binding!!

    private lateinit var rectangularWidgetRecyclerViewAdapter: RectangularWidgetRecyclerViewAdapter



    private val onDashboardItemClicked = { item: BottomSheetOption ->
        when(item.index){
            0 -> findNavController().navigate(R.id.action_homeDashboardFragment_to_cardlessWithdrawal)
            1 -> findNavController().navigate( R.id.action_homeDashboardFragment_to_mobileMoneyWithdrawal)
            2 -> findNavController().navigate(R.id.action_homeDashboardFragment_to_school_fees_Fragment)
        }
        dismiss()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  CashWithdrawalBottomSheetBinding.inflate(inflater, container, false)


        binding.bottomSheetTitleText.text = bottomsheetTitleText


        binding.closeButton.setOnClickListener{
            dismiss()
        }




        // Bottom sheet adapter
        rectangularWidgetRecyclerViewAdapter = RectangularWidgetRecyclerViewAdapter(items, onDashboardItemClicked)

        //recyclerview viewbinding
        binding.bottomSheetRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = rectangularWidgetRecyclerViewAdapter
        }



        dialog?.setCanceledOnTouchOutside(true)
        dialog?.setCancelable(true)


        return binding.root
    }


    override fun onStart() {
        super.onStart()

        val bottomSheet = dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

        bottomSheet?.let {
            val behavior = BottomSheetBehavior.from(it)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED

            // Enable swipe-down to dismiss
            behavior.isHideable = true
            behavior.skipCollapsed = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(items: List<BottomSheetOption>, bottomsheetTitleText: String): BottomSheetDialogFragment {
            return BottonsheetFragement(items, bottomsheetTitleText)
        }
    }

}