package com.example.eclecticbank.ViewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class onBoardingViewModel: ViewModel() {

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
