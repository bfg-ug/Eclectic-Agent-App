package com.example.eclecticbank.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eclecticbank.model.Schools
import com.example.eclecticbank.repository.SchoolsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolsViewModel@Inject constructor(private  val schoolsRepository: SchoolsRepository):ViewModel() {

    private val _schools = MutableLiveData<List<Schools>>()
    val schools: LiveData<List<Schools>> = _schools


    fun addSchools(schools: List<Schools>){
        CoroutineScope(Dispatchers.IO).launch{
            schoolsRepository.insertschools(schools)
        }

    }

    val allSchools: LiveData<List<Schools>> = schoolsRepository.schoolData



    fun loadSchoolsByType(type: String){
        viewModelScope.launch {
            val response = schoolsRepository.getSchoolByType(type)
            _schools.value = response
        }
    }

    fun searchSchools(query: String){
        viewModelScope.launch {
            val response = schoolsRepository.getSchoolByName(query)
            _schools.value = response
        }
    }




}