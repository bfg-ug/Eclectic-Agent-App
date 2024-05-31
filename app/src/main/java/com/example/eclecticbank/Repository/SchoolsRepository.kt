package com.example.eclecticbank.Repository

import androidx.lifecycle.LiveData
import com.example.eclecticbank.Models.SchoolDao
import com.example.eclecticbank.Models.Schools
import dagger.Provides
import javax.inject.Inject

interface SchoolsRepository {

    suspend fun insertschools(schools: List<Schools>)

    val schoolData: LiveData<List<Schools>>
}

class SchoolsRepositoryImpl @Inject constructor(
    private val schoolDao: SchoolDao
):SchoolsRepository{
    override suspend fun insertschools(schools: List<Schools>) {
        return schoolDao.insertSchools(schools)
    }

    override val schoolData: LiveData<List<Schools>>
        get() = schoolDao.getSchoolData()


}