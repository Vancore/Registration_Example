package com.example.registrationzkb.usecases

import com.example.registrationzkb.data.RegistrationInput
import com.example.registrationzkb.utils.Utils.Companion.EMAIL_ADDRESS_CUSTOM_PATTERN
import com.example.registrationzkb.utils.Utils.Companion.maximumCalendarTime
import com.example.registrationzkb.utils.Utils.Companion.minimumCalendarTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*
import javax.inject.Inject

data class ValidationState(
    val inputIsValid: Boolean = false,
    val nameInputValid: Boolean = false,
    val emailInputValid: Boolean = false,
    val dateInputValid: Boolean = false,
    val nameInput: String = "",
    val emailInput: String = "",
    val dateInput: Long = 0L
)

class ValidationUseCase @Inject constructor(
    // a repository which fetches the current global time
) {
    private val _validationState = MutableStateFlow(ValidationState())
    val validationState: StateFlow<ValidationState> = _validationState.asStateFlow()

    fun validateInput(input: RegistrationInput) {
        _validationState.update {
            val nameIsValid = validateName(name = input.name)
            val emailIsValid = validateEmail(email = input.email)
            val dateIsValid = validateDate(date = input.birthday)
            val inputIsValid = nameIsValid && emailIsValid && dateIsValid
            if(inputIsValid) {
                it.copy(
                    inputIsValid = inputIsValid,
                    nameInputValid = nameIsValid,
                    emailInputValid = emailIsValid,
                    dateInputValid = dateIsValid,
                    nameInput = input.name,
                    emailInput = input.email,
                    dateInput = input.birthday
                )
            } else {
                it.copy(
                    inputIsValid = inputIsValid,
                    nameInputValid = nameIsValid,
                    emailInputValid = emailIsValid,
                    dateInputValid = dateIsValid
                )
            }
        }
    }

    private fun validateName(name: String): Boolean {
        return name.isNotEmpty()
    }

    private fun validateEmail(email: String): Boolean {
        if (email.isEmpty()) return false
        return EMAIL_ADDRESS_CUSTOM_PATTERN.matcher(email).matches()
    }

    private fun validateDate(date: Long): Boolean {
        val inputDate = Date(date)
        return inputDate.after(minimumCalendarTime()) && inputDate.before(maximumCalendarTime())
    }
}