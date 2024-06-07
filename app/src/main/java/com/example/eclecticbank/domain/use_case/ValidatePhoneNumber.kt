package com.example.eclecticbank.domain.use_case

import android.util.Patterns
import com.example.eclecticbank.model.ValidationResult

class ValidatePhoneNumber {

    fun excute(phoneNumber: String):ValidationResult{
        return when{
            phoneNumber.isBlank() -> ValidationResult(
                isValid = false,
                message = "Phone number cannot be blank"
            )
            !Patterns.PHONE.matcher(phoneNumber).matches() -> ValidationResult(
                isValid = false,
                message = "Phone number is not valid"
            )
            phoneNumber.length < 10 -> ValidationResult(
                isValid = false,
                message = "Phone number must be 10 digits"
            )
            else -> ValidationResult(
                isValid = true,
                message = null
            )
        }

    }
}