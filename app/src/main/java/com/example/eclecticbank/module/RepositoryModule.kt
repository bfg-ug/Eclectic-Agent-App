package com.example.eclecticbank.module

import com.example.eclecticbank.data.room.dao.SchoolDao
import com.example.eclecticbank.domain.use_case.ValidateAccountNumber
import com.example.eclecticbank.domain.use_case.ValidateAmount
import com.example.eclecticbank.domain.use_case.ValidateNarration
import com.example.eclecticbank.domain.use_case.ValidatePhoneNumber
import com.example.eclecticbank.repository.SchoolsRepository
import com.example.eclecticbank.repository.implementation.SchoolsRepositoryImpl
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