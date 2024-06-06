package com.example.eclecticbank.domain.use_case

import com.example.eclecticbank.model.ValidationResult

class ValidateAmount {
    fun excute(amount: String): ValidationResult {

        return when{
            amount.isEmpty() -> ValidationResult(
                isValid = false,
                message = "Amount cannot be empty"
            )
            amount.toDouble() <= 0 -> ValidationResult(
                isValid = false,
                message = "Amount cannot be zero or negative"
            )
            amount.toDouble() > 500000 -> ValidationResult(
                isValid = false,
                message = "Amount cannot be more than 500000"
            )
            else -> ValidationResult(
                isValid = true,
                message = "Account number is valid"
            )
        }

    }
}