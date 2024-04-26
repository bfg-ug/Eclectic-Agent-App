package com.example.eclecticbank.ViewModels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eclecticbank.Models.ApiService
import com.example.eclecticbank.Models.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class UserViewModel @Inject constructor (private val apiService: ApiService): ViewModel(){

    private val _userInfo = MutableLiveData<Users?>()
    val userInfo: MutableLiveData<Users?> = _userInfo

 fun fetchCustomerData(){
     viewModelScope.launch {
         try {
             val user = apiService.fetchData()


             _userInfo.value = user
             Log.d("error", user.bankName )

         }catch (e:Exception){

             Log.d("error", "fetchCustomerData: ${e}" )

         }
     }
 }
}