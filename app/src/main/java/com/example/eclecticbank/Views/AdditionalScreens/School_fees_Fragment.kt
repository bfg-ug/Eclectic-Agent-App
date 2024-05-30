package com.example.eclecticbank.Views.AdditionalScreens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eclecticbank.Models.SchoolFeesOption
import com.example.eclecticbank.Models.Schools
import com.example.eclecticbank.Models.SchoolFeesRecyclerViewAdapter
import com.example.eclecticbank.R
import com.example.eclecticbank.ViewModels.SchoolsViewModel
import com.example.eclecticbank.databinding.FragmentSchoolFeesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class School_fees_Fragment : Fragment() {

    private var _binding: FragmentSchoolFeesBinding? = null
    private val binding get() = _binding!!

    private lateinit var recylerViewAdapter: SchoolFeesRecyclerViewAdapter

    private val schoolsViewModel: SchoolsViewModel by viewModels()


    private val items = listOf(
        Schools(1,"Taibah international","School","+25677777777","Wakiso", "112980"),
        Schools(2,"Viva international", "School" ,"+25677777777","Wakiso", "112980"),
        Schools(3,"Budo international", "School","+25677777777","Wakiso", "112980"),
        Schools(1,"Taibah international","Institution","+25677777777","Wakiso", "112980"),
        Schools(2,"Strathmore University", "Institiution" ,"+25677777777","Wakiso", "112980"),
        Schools(3,"University of Nairobi", "School","+25677777777","Wakiso", "112980"),
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




//        Searchview binding
        binding.searchview.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })




        //Focused Colors
        val defaultColor = ContextCompat.getColor(requireContext(), R.color.light_grey)
        val focusedColor = ContextCompat.getColor(requireContext(), R.color.blue)
        val defaultFontColor = ContextCompat.getColor(requireContext(), R.color.grey)
        val focusedFontColor = ContextCompat.getColor(requireContext(), R.color.white)



        //School button
        binding.schoolButton.setOnClickListener{
            binding.schoolButton.setBackgroundColor(focusedColor)
            binding.institutionButton.setBackgroundColor(defaultColor)
            binding.schoolButton.setTextColor(focusedFontColor)
            binding.institutionButton.setTextColor(defaultFontColor)

            filterByType("School")

        }

        //Institution button
        binding.institutionButton.setOnClickListener{
            binding.schoolButton.setBackgroundColor(defaultColor)
            binding.institutionButton.setBackgroundColor(focusedColor)
            binding.schoolButton.setTextColor(defaultFontColor)
            binding.institutionButton.setTextColor(focusedFontColor)

            filterByType("Institution")

        }


        schoolsViewModel.addSchools(items)
        schoolsViewModel.getSchoolData()

        Log.d("room Response", schoolsViewModel.getSchoolData().toString())





        //Recyclerview Adapter
        recylerViewAdapter = SchoolFeesRecyclerViewAdapter(items)

        //recyclerview viewbinding
        binding.schoolListRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = recylerViewAdapter
        }




        return binding.root
    }
//    Search bar Filter
    private fun filter(text: String) {
        val filteredList = items.filter { it.schoolName.contains(text, ignoreCase = true) }
        recylerViewAdapter.filterList(filteredList)
    }

    //Filter by type
    private fun filterByType(text: String) {
        val filteredList = items.filter { it.schoolType.contains(text, ignoreCase = true) }
        recylerViewAdapter.filterList(filteredList)
    }

}