package com.example.eclecticbank.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eclecticbank.Models.Schools
import com.example.eclecticbank.Repository.SchoolsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolsViewModel@Inject constructor(private  val schoolsRepository: SchoolsRepository):ViewModel() {


    fun addSchools(schools: List<Schools>){
        CoroutineScope(Dispatchers.IO).launch{
            schoolsRepository.insertschools(schools)
        }

    }


    fun getSchoolData(){
        CoroutineScope(Dispatchers.IO).launch{
           schoolsRepository.getSchools()
        }

    }


}