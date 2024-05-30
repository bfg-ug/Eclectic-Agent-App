package com.example.eclecticbank.Models

import com.example.eclecticbank.Repository.SchoolsRepository
import com.example.eclecticbank.Repository.SchoolsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideSchoolsRepository(schoolDao: SchoolDao): SchoolsRepository {
        return SchoolsRepositoryImpl(schoolDao)


    }


}