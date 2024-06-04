package com.example.eclecticbank.module

import android.content.Context
import androidx.room.Room
import co.infinum.retromock.Retromock
import com.example.eclecticbank.data.api.ApiService
import com.example.eclecticbank.data.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "appDatabase.db")
            .fallbackToDestructiveMigration()
            .build()

    }


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://example.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetroMock(retrofit: Retrofit): Retromock {
        return Retromock.Builder()
            .retrofit(retrofit)
            .build();
    }

    @Provides
    fun provideMockTest(retromock: Retromock): ApiService {
        return retromock.create(ApiService::class.java)
    }


    @Provides
    fun provideUserDAO(database: AppDatabase) = database.schoolDao()






}

