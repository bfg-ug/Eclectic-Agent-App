package com.example.eclecticbank.Views.AdditionalScreens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eclecticbank.Models.BottomSheetOption
import com.example.eclecticbank.Models.SchoolFeesOption
import com.example.eclecticbank.Models.SchoolFeesRecyclerViewAdapter
import com.example.eclecticbank.R
import com.example.eclecticbank.databinding.FragmentSchoolFeesBinding


class School_fees_Fragment : Fragment() {

    private var _binding: FragmentSchoolFeesBinding? = null
    private val binding get() = _binding!!

    private lateinit var recylerViewAdapter: SchoolFeesRecyclerViewAdapter


    private val items = listOf(
        SchoolFeesOption(0,"Direct collection", R.drawable.deposit_icon,"school" ),
        SchoolFeesOption(1,"Indirect collections", R.drawable.payment_icon, "school"),
        SchoolFeesOption(2,"School fees", R.drawable.school_icon,"institution"),
        SchoolFeesOption(3,"Goverment collection", R.drawable.goverment_icon, "institution"),
    )





    private val schoolListItemOnClicked = { item: SchoolFeesOption ->
        when(item.index){
            0 -> findNavController().navigate(R.id.action_homeDashboardFragment_to_cardlessWithdrawal)
            1 -> findNavController().navigate( R.id.action_homeDashboardFragment_to_mobileMoneyWithdrawal)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSchoolFeesBinding.inflate(inflater, container, false)



        binding.toolbar.setOnClickListener{
            findNavController().navigate(R.id.action_school_fees_Fragment_to_homeDashboardFragment)
        }





        recylerViewAdapter = SchoolFeesRecyclerViewAdapter(items, schoolListItemOnClicked)

        //recyclerview viewbinding
        binding.schoolListRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = recylerViewAdapter
        }





        val defaultColor = ContextCompat.getColor(requireContext(), R.color.light_grey)
        val focusedColor = ContextCompat.getColor(requireContext(), R.color.blue)
        val defaultFontColor = ContextCompat.getColor(requireContext(), R.color.grey)
        val focusedFontColor = ContextCompat.getColor(requireContext(), R.color.white)




        binding.schoolButton.setOnClickListener{
            binding.schoolButton.setBackgroundColor(focusedColor)
            binding.institutionButton.setBackgroundColor(defaultColor)
            binding.schoolButton.setTextColor(focusedFontColor)
            binding.institutionButton.setTextColor(defaultFontColor)

            filterByType("school")

        }

        binding.institutionButton.setOnClickListener{
            binding.schoolButton.setBackgroundColor(defaultColor)
            binding.institutionButton.setBackgroundColor(focusedColor)
            binding.schoolButton.setTextColor(defaultFontColor)
            binding.institutionButton.setTextColor(focusedFontColor)

            filterByType("institution")

        }

        binding.searchview.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })



        return binding.root
    }

    private fun filter(text: String) {
        val filteredList = items.filter { it.widgetTitle.contains(text, ignoreCase = true) }
        recylerViewAdapter.filterList(filteredList)
    }

    private fun filterByType(text: String) {
        val filteredList = items.filter { it.companyType.contains(text, ignoreCase = true) }
        recylerViewAdapter.filterList(filteredList)
    }

}