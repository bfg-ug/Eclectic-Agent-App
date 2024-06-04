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



}