package com.example.eclecticbank.repository

import androidx.lifecycle.LiveData
import com.example.eclecticbank.data.room.dao.SchoolDao
import com.example.eclecticbank.model.Schools
import javax.inject.Inject

interface SchoolsRepository {

    suspend fun insertschools(schools: List<Schools>)

    suspend fun getSchoolByType(type: String):List<Schools>

    suspend fun getSchoolByName(query: String):List<Schools>

    val schoolData: LiveData<List<Schools>>
}
