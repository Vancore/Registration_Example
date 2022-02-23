package com.example.registrationzkb.screens.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registrationzkb.data.DataStoreManager
import com.example.registrationzkb.data.RegistrationInput
import com.example.registrationzkb.usecases.ValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    fun saveInputLocally(registrationInput: RegistrationInput) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.saveUserInput(registrationInput = registrationInput)
        }
    }

}