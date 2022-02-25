package com.example.registrationzkb

import com.example.registrationzkb.data.RegistrationInput

object RegistrationInputCreator {

    fun getInvalidDateRegistrationInput(): RegistrationInput {
        return RegistrationInput(
            name = RegistrationInputConstants.REGISTRATION_INPUT_NAME,
            email = RegistrationInputConstants.REGISTRATION_INPUT_EMAIL,
            birthday = RegistrationInputConstants.REGISTRATION_INPUT_DATE_INVALID
        )
    }

    fun getInvalidEmailRegistrationInput(): RegistrationInput {
        return RegistrationInput(
            name = RegistrationInputConstants.REGISTRATION_INPUT_NAME,
            email = RegistrationInputConstants.REGISTRATION_INPUT_EMAIL_INVALID,
            birthday = RegistrationInputConstants.REGISTRATION_INPUT_DATE
        )
    }

    fun getInvalidNameRegistrationInput(): RegistrationInput {
        return RegistrationInput(
            name = RegistrationInputConstants.REGISTRATION_INPUT_NAME_INVALID,
            email = RegistrationInputConstants.REGISTRATION_INPUT_EMAIL,
            birthday = RegistrationInputConstants.REGISTRATION_INPUT_DATE
        )
    }

    fun getValidRegistrationInput(): RegistrationInput {
        return RegistrationInput(
            name = RegistrationInputConstants.REGISTRATION_INPUT_NAME,
            email = RegistrationInputConstants.REGISTRATION_INPUT_EMAIL,
            birthday = RegistrationInputConstants.REGISTRATION_INPUT_DATE
        )
    }
}