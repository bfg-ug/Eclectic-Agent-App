package com.example.eclecticbank.domain.use_case

import android.util.Patterns
import com.example.eclecticbank.model.ValidationResult

class ValidateRecipient {

    fun excute(recipientphoneNumber: String): ValidationResult {
        return when {
            recipientphoneNumber.isBlank() -> ValidationResult(
                isValid = false,
                message = "Phone number cannot be blank"
            )

            !Patterns.PHONE.matcher(recipientphoneNumber).matches() -> ValidationResult(
                isValid = false,
                message = "Phone number is not valid"
            )

            else -> ValidationResult(
                isValid = true,
                message = null
            )
        }
    }
}