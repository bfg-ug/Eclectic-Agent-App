package com.example.eclecticbank.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.eclecticbank.model.Schools

@Dao
interface SchoolDao {
     
    
    @Upsert
    suspend fun insertSchools(schools: List<Schools>)


    @Query("SELECT * FROM SCHOOL_TABLE")
    fun getSchoolData(): LiveData<List<Schools>>


    @Query("SELECT * FROM SCHOOL_TABLE WHERE school_type = :schoolType")
    fun getSchoolDataByType(schoolType: String): List<Schools>

    @Query("SELECT * FROM SCHOOL_TABLE WHERE school_name = :schoolName")
    suspend fun getSchoolDataByName(schoolName: String): List<Schools>



}