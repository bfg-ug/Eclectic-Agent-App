package com.example.eclecticbank.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eclecticbank.domain.use_case.ValidateAccountNumber
import com.example.eclecticbank.domain.use_case.ValidateAmount
import com.example.eclecticbank.domain.use_case.ValidateNarration
import com.example.eclecticbank.domain.use_case.ValidatePhoneNumber
import com.example.eclecticbank.domain.use_case.ValidateRecipient
import com.example.eclecticbank.model.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputValidationViewModel @Inject constructor(
    private val validateAccountNumber: ValidateAccountNumber,
    private val validatePhoneNumber: ValidatePhoneNumber,
    private val validateAmount: ValidateAmount,
    private val validateNarration: ValidateNarration,
    private val validateRecipient: ValidateRecipient,
    private val dispatcher: CoroutineDispatcher
):ViewModel() {

    private val _accountNumberValidation = MutableLiveData<ValidationResult>()
    val accountNumberValidation: LiveData<ValidationResult> = _accountNumberValidation

    private val _phoneNumberValidation = MutableLiveData<ValidationResult>()
    val phoneNumberValidation: LiveData<ValidationResult> = _phoneNumberValidation

    private val _amountValidation = MutableLiveData<ValidationResult>()
    val amountValidation: LiveData<ValidationResult> = _amountValidation

    private val _narrationValidation = MutableLiveData<ValidationResult>()
    val narrationValidation: LiveData<ValidationResult> = _narrationValidation


    private val _recipientValidation = MutableLiveData<ValidationResult>()
    val recipientValidation: LiveData<ValidationResult> = _recipientValidation

    private val _isDepositFormValid = MutableLiveData<Boolean>()
    val isDepositFormValid: LiveData<Boolean> = _isDepositFormValid


    private val _isCardlessWithdrawalFormValid = MutableLiveData<Boolean>()
    val isCardlessWithdrawalFormValid: LiveData<Boolean> = _isCardlessWithdrawalFormValid

    fun validateAccountnumber(accountNumber: String) {
        viewModelScope.launch(dispatcher) {
            val result = validateAccountNumber.execute(accountNumber)
            _accountNumberValidation.postValue(result)
            depositFormValidity()
        }
    }

    fun validatePhoneNumber(phoneNumber: String) {
        viewModelScope.launch(dispatcher) {
            val result = validatePhoneNumber.excute(phoneNumber)
            _phoneNumberValidation.postValue(result)
            depositFormValidity()
        }
    }

    fun validateAmount(amount: String) {
        viewModelScope.launch(dispatcher) {
            val result = validateAmount.excute(amount)
            _amountValidation.postValue(result)
            depositFormValidity()
        }
    }

    fun validateNarration(narration: String) {
        viewModelScope.launch(dispatcher) {
            val result = validateNarration.excute(narration)
            _narrationValidation.postValue(result)
            depositFormValidity()
        }
    }

    fun validateRecipient(recipient: String) {
        viewModelScope.launch(dispatcher) {
            val result = validateRecipient.excute(recipient)
            _recipientValidation.postValue(result)
            depositFormValidity()

        }
    }

    private fun depositFormValidity() {
        val isValid = listOf(
            _accountNumberValidation.value?.isValid,
            _phoneNumberValidation.value?.isValid,
            _amountValidation.value?.isValid,
            _narrationValidation.value?.isValid
        ).all { it == true }
        _isDepositFormValid.postValue(isValid)
    }

    private fun cardlessWithdrawalFormValidity(){
        val isValid = listOf(
            _phoneNumberValidation.value?.isValid,
            _recipientValidation.value?.isValid,
        ).all { it == true }
        _isCardlessWithdrawalFormValid.postValue(isValid)
    }





}