package com.example.eclecticbank.domain.use_case

import com.example.eclecticbank.model.ValidationResult

class ValidateAccountNumber {
    fun execute(accountNumber: String): ValidationResult {

        return  when{
            accountNumber.isBlank() -> ValidationResult(
                isValid = false,
                message = "Account number cannot be blank")
            accountNumber.length < 10 -> ValidationResult(
                isValid = false,
                message = "Account number must be atleast 10 digits"
            )
            accountNumber.any { it.isLetter() } -> ValidationResult(
                isValid = false,
                message = "Account number cannot contain letters"
            )
            else -> ValidationResult(
                isValid = true,
                null
            )

        }

    }
}