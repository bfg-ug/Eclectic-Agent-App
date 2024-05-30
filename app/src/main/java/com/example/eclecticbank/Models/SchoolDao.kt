package com.example.eclecticbank.Models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface SchoolDao {
     
    
    @Upsert
    suspend fun insertSchools(schools: List<Schools>)


    @Query("SELECT * FROM SCHOOL_TABLE")
    fun getSchoolData(): List<Schools>



}