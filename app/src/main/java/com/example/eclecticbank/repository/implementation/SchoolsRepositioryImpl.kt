package com.example.eclecticbank.repository.implementation

import androidx.lifecycle.LiveData
import com.example.eclecticbank.data.room.dao.SchoolDao
import com.example.eclecticbank.model.Schools
import com.example.eclecticbank.repository.SchoolsRepository
import javax.inject.Inject

class SchoolsRepositoryImpl @Inject constructor(
    private val schoolDao: SchoolDao
): SchoolsRepository {
    override suspend fun insertschools(schools: List<Schools>) {
        return schoolDao.insertSchools(schools)
    }

    override val schoolData: LiveData<List<Schools>>
        get() = schoolDao.getSchoolData()


}