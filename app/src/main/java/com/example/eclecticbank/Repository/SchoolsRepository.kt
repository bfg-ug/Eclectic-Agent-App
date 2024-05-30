package com.example.eclecticbank.Repository

import com.example.eclecticbank.Models.SchoolDao
import com.example.eclecticbank.Models.Schools
import dagger.Provides
import javax.inject.Inject

interface SchoolsRepository {

    suspend fun insertschools(schools: List<Schools>)

    fun getSchools(): List<Schools>
}

class SchoolsRepositoryImpl @Inject constructor(
    private val schoolDao: SchoolDao
):SchoolsRepository{
    override suspend fun insertschools(schools: List<Schools>) {
        return schoolDao.insertSchools(schools)
    }

    override fun getSchools(): List<Schools> {
        return schoolDao.getSchoolData()
    }

}