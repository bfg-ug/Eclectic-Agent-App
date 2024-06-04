package com.example.eclecticbank.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eclecticbank.data.room.dao.SchoolDao
import com.example.eclecticbank.model.Schools

@Database(entities = [Schools::class], version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){
    abstract fun schoolDao(): SchoolDao

}