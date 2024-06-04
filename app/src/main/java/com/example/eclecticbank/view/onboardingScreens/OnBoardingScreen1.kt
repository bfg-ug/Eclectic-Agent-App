package com.example.eclecticbank.view.onboardingScreens

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.eclecticbank.R
import com.example.eclecticbank.databinding.FragmentOnBoardingScreen1Binding
import java.util.Locale


class OnBoardingScreen1 : Fragment() {
    private var _binding:FragmentOnBoardingScreen1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentOnBoardingScreen1Binding.inflate(inflater, container, false)

        // String Array of available language options
        val languages = resources.getStringArray(R.array.languages)

        //Array adapter
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdownitem,languages)

        binding.autoCompleteTextview.setAdapter(arrayAdapter)

        //Autocomplete textfield binding
        binding.autoCompleteTextview.setOnItemClickListener { parent, view, position, id ->
            val selectedLanguage = parent.getItemAtPosition(position) as String
            setLocale(selectedLanguage)
        }


        //Getting Onboarding viewpager
        val viewpager = activity?.findViewById<ViewPager2>(R.id.viewPager)


        //next button
        binding.nextArrow.setOnClickListener{
            viewpager?.currentItem = viewpager?.currentItem!! + 1
        }

        //skip button
        binding.skiptext.setOnClickListener{
            viewpager?.currentItem = 3
        }



        return binding.root




    }

    //Change language function
    private fun setLocale(language: String) {
        val locale = when (language) {
            "Swahili"-> Locale("sw")
            "French" -> Locale("fr")
            "Spanish" -> Locale("es")
            else -> Locale.getDefault()
        }
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, requireContext().resources.displayMetrics)
        requireActivity().recreate()


        binding.autoCompleteTextview.text = null

    }




}