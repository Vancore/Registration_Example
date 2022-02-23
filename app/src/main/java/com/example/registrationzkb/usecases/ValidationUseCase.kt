package com.example.registrationzkb.usecases

import com.example.registrationzkb.data.RegistrationInput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import javax.inject.Inject

data class ValidationState(
    val inputIsValid: Boolean = false,
    val nameInputValid: Boolean = false,
    val emailInputValid: Boolean = false,
    val dateInputValid: Boolean = false,
)

class ValidationUseCase @Inject constructor(
    // a repository which fetches the current global time
){
    private val _validationState = MutableStateFlow(ValidationState())
    val validationState: StateFlow<ValidationState> = _validationState.asStateFlow()

    fun validateInput(input: RegistrationInput) {
        _validationState.update {
            val nameIsValid = validateName(name = input.name)
            val emailIsValid = validateEmail(email = input.email)
            val dateIsValid = validateDate(date = input.birthday)
            it.copy(
                inputIsValid = nameIsValid && emailIsValid && dateIsValid,
                nameInputValid = nameIsValid,
                emailInputValid = emailIsValid,
                dateInputValid = dateIsValid
            )
        }
    }

    private fun validateName(name: String): Boolean {
        return name.isNotEmpty()
    }

    private fun validateEmail(email: String): Boolean {
        if (email.isEmpty()) return false
        return EMAIL_ADDRESS_CUSTOM.matcher(email).matches() // ToDo: Allow only input with at least 2 chars after '.'
    }

    private fun validateDate(date: Long): Boolean {
        val inputDate = Date(date)
        return inputDate.after(minimumCalendarTime()) && inputDate.before(maximumCalendarTime())
    }

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        return format.format(date)
    }

    companion object {
        fun minimumCalendarTime(): Date {
            val minimumCalendarTime = Calendar.getInstance()
            minimumCalendarTime[Calendar.YEAR] = 1900
            minimumCalendarTime[Calendar.MONTH] = Calendar.JANUARY
            minimumCalendarTime[Calendar.DAY_OF_MONTH] = 1
            return minimumCalendarTime.time
        }

        fun maximumCalendarTime(): Date {
            val maximumCalendarTime = Calendar.getInstance()
            maximumCalendarTime[Calendar.YEAR] = 2021
            maximumCalendarTime[Calendar.MONTH] = Calendar.DECEMBER
            maximumCalendarTime[Calendar.DAY_OF_MONTH] = 31
            return maximumCalendarTime.time
        }

        val EMAIL_ADDRESS_CUSTOM: Pattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}" +
                    ")+"
        )
    }

}