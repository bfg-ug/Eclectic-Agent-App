package com.example.eclecticbank.domain.use_case

import com.example.eclecticbank.model.ValidationResult

class ValidateNarration {
    fun excute(narration: String): ValidationResult {

        return when{
            narration.isBlank() -> ValidationResult(
                isValid = false,
                message = "Narration cannot be blank"
            )
            narration.contains(Regex("[0-9]")) -> ValidationResult(
                isValid = false,
                message = "Narration cannot contain Digit"
            )
            else -> ValidationResult(
                isValid = true,
                message = "Narration is valid"
            )
        }

    }
}