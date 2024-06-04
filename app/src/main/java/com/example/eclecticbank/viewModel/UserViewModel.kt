package com.example.eclecticbank.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eclecticbank.data.api.ApiService
import com.example.eclecticbank.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class UserViewModel @Inject constructor (private val apiService: ApiService): ViewModel(){

    private val _userInfo = MutableLiveData<User?>()
    val userInfo: MutableLiveData<User?> = _userInfo

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