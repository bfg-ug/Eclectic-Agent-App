package com.example.eclecticbank.ViewModels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eclecticbank.Models.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor (private val apiService: ApiService): ViewModel(){

 fun fetchCustomerData(){
     viewModelScope.launch {
         try {
             val response = apiService.fetchData()
             Log.d("error", "fetchCustomerData: ${response}" )
         }catch (e:Exception){

             Log.d("error", "fetchCustomerData: ${e}" )

         }
     }
 }
}