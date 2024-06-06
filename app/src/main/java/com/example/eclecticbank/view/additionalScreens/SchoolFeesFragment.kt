package com.example.eclecticbank.view.additionalScreens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eclecticbank.model.Schools
import com.example.eclecticbank.view.adapter.SchoolFeesRecyclerViewAdapter
import com.example.eclecticbank.R
import com.example.eclecticbank.viewModel.SchoolsViewModel
import com.example.eclecticbank.databinding.FragmentSchoolFeesBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolFeesFragment : Fragment() {

    private var _binding: FragmentSchoolFeesBinding? = null
    private val binding get() = _binding!!

    private lateinit var recylerViewAdapter: SchoolFeesRecyclerViewAdapter

    private val schoolsViewModel: SchoolsViewModel by viewModels()


    private val items = listOf(
        Schools(1,"Taibah international","School","+25677777777","Wakiso", "112980"),
        Schools(2,"Viva international", "School" ,"+25677777777","Wakiso", "112980"),
        Schools(3,"Budo international", "School","+25677777777","Wakiso", "112980"),
        Schools(4,"Taibah international","Institution","+25677777777","Wakiso", "112980"),
        Schools(5,"Strathmore University", "Institiution" ,"+25677777777","Wakiso", "112980"),
        Schools(6,"University of Nairobi", "School","+25677777777","Wakiso", "112980"),
    )






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSchoolFeesBinding.inflate(inflater, container, false)

        val tabitems = arrayListOf(
            "School",
            "Institution",
        )

        schoolsViewModel.addSchools(items)


        schoolsViewModel.allSchools.observe(viewLifecycleOwner, Observer { schools ->
            schools?.let { recylerViewAdapter.setSchools(it)
            }
        })




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("School"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Institution"))
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

        val defaultColor = ContextCompat.getColor(requireContext(), R.color.light_grey)
        val focusedColor = ContextCompat.getColor(requireContext(), R.color.blue)
        val defaultFontColor = ContextCompat.getColor(requireContext(), R.color.grey)
        val focusedFontColor = ContextCompat.getColor(requireContext(), R.color.white)



        // Customize the tab layout
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.drawable.tab_layout_tab_focused)

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.drawable.tablayout_tab_unfocused)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Handle reselection if needed
                tab.view.setBackgroundResource(R.drawable.tab_layout_tab_focused)
            }
        })



        //Recyclerview Adapter
        recylerViewAdapter = SchoolFeesRecyclerViewAdapter(){items ->
            navigateToDetailsScreen(items)

        }

        //recyclerview viewbinding
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = recylerViewAdapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
    }

    private fun navigateToDetailsScreen(item: Schools) {
        val action = SchoolFeesFragmentDirections.actionSchoolFeesFragmentToSchoolFeesPaymentFragment(
            schoolName = item.schoolName,
            schoolType = item.schoolType,
            schoolLocation = item.schoolAddress
        )
        findNavController().navigate(action)
    }

    //    Search bar Filter
    private fun filter(text: String) {
        schoolsViewModel.searchSchools(text)
    }


}