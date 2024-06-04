package com.example.eclecticbank.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SCHOOL_TABLE")
data class Schools(
     @PrimaryKey(autoGenerate = true) val id: Int,
     @ColumnInfo(name = "school_name")val schoolName: String,
     @ColumnInfo(name = "school_type") val schoolType: String,
     @ColumnInfo(name = "school_address") val schoolAddress:String,
     @ColumnInfo(name = "school_phone_number") val schoolPhoneNumber:String,
     @ColumnInfo(name = "school_till_number") val schoolTillNumber:String,
 )