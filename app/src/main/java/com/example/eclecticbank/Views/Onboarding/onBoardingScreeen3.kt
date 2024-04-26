package com.example.eclecticbank.Views.Onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.eclecticbank.R
import com.example.eclecticbank.ViewModels.onBoardingViewModel
import com.example.eclecticbank.databinding.FragmentOnBoardingScreeen3Binding

class onBoardingScreeen3 : Fragment() {

    private var _binding:FragmentOnBoardingScreeen3Binding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel:onBoardingViewModel
    private lateinit var mutiplePermissionLauncher: ActivityResultLauncher<Array<String>>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        viewModel = ViewModelProvider(this)[onBoardingViewModel::class.java]


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

        // Inflate the layout for this fragment
        _binding = FragmentOnBoardingScreeen3Binding.inflate(inflater, container, false)

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

        return binding.root
    }


}