package com.example.eclecticbank.module

import com.example.eclecticbank.domain.use_case.ValidateAccountNumber
import com.example.eclecticbank.domain.use_case.ValidateAmount
import com.example.eclecticbank.domain.use_case.ValidateNarration
import com.example.eclecticbank.domain.use_case.ValidatePhoneNumber
import com.example.eclecticbank.domain.use_case.ValidateRecipient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideAccountNumberValidation(): ValidateAccountNumber = ValidateAccountNumber()


    @Provides
    @Singleton
    fun providePhoneNumberValidation(): ValidatePhoneNumber = ValidatePhoneNumber()

    @Provides
    @Singleton
    fun provideAmountValidation(): ValidateAmount = ValidateAmount()

    @Provides
    @Singleton
    fun provideNarrationValidation(): ValidateNarration = ValidateNarration()


    @Provides
    @Singleton
    fun provideRecipientValidation(): ValidateRecipient = ValidateRecipient()


}