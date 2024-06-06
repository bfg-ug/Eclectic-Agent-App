package com.example.eclecticbank.view.onboardingScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.eclecticbank.R
import com.example.eclecticbank.viewModel.OnBoardingViewModel
import com.example.eclecticbank.databinding.FragmentOnBoardingScreeen3Binding

class OnBoardingScreen3 : Fragment() {

    private var _binding:FragmentOnBoardingScreeen3Binding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel:OnBoardingViewModel
    private lateinit var mutiplePermissionLauncher: ActivityResultLauncher<Array<String>>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOnBoardingScreeen3Binding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[OnBoardingViewModel::class.java]


        viewModel.visibePermissionDialogueQueue


        //Request multiple permissions launcher
        mutiplePermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { perms ->
            perms.keys.forEach { permission ->
                viewModel.onPermissionResult(
                    permission,
                    perms[permission] == true
                )
            }
        }


        val viewpager = activity?.findViewById<ViewPager2>(R.id.viewPager)


        //Next Arrow
        binding.nextArrow.setOnClickListener(){
            viewpager?.currentItem = viewpager?.currentItem!! + 1
        }

        //Skip Arrow
        binding.skiptext.setOnClickListener(){
            viewpager?.currentItem = 3
        }


        //Request permissions
        binding.givePermissionButton.setOnClickListener(){

            mutiplePermissionLauncher.launch(
                arrayOf(
                    android.Manifest.permission.READ_CONTACTS,
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )

        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}