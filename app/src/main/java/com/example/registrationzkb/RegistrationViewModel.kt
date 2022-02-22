package com.example.registrationzkb

import androidx.lifecycle.ViewModel
import com.example.registrationzkb.data.DataStoreManager
import com.example.registrationzkb.data.RegistrationInput
import com.example.registrationzkb.usecases.ValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val validationUseCase: ValidationUseCase
) : ViewModel() {

    var validationState = validationUseCase.validationState

    fun validateInput(registrationInput: RegistrationInput) {
        validationUseCase.validateInput(registrationInput)
    }
}