package com.example.eclecticbank.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class OnBoardingViewModel: ViewModel() {

    val visibePermissionDialogueQueue = mutableStateListOf<String>()


    fun onPermissionResult(
        permission:String,
        isGranted:Boolean
    ){
        if (!isGranted){
            visibePermissionDialogueQueue.add(0, permission)
        }
    }

}
