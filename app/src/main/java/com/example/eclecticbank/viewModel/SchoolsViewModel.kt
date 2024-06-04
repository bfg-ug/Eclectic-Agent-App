package com.example.eclecticbank.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.eclecticbank.model.Schools
import com.example.eclecticbank.repository.SchoolsRepository
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

    val allSchools: LiveData<List<Schools>> = schoolsRepository.schoolData




}