package com.example.registrationzkb.screens.success

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registrationzkb.data.DataStoreManager
import com.example.registrationzkb.data.RegistrationInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class InputLoadingState(
    val input: RegistrationInput = RegistrationInput(),
    val loadingState: LoadingState = LoadingState.Idle
)

@HiltViewModel
class SuccessViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _registrationInput = MutableStateFlow(InputLoadingState())
    val registrationInput: StateFlow<InputLoadingState> = _registrationInput.asStateFlow()

    init {
        loadUserInputs()
    }

    private fun loadUserInputs() {
        _registrationInput.update { it.copy(loadingState = LoadingState.Loading) }

        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.loadUserInput().collect { registrationInput ->
                _registrationInput.update { loadingState ->
                    loadingState.copy(input = registrationInput, loadingState = LoadingState.Idle)
                }
            }
        }
    }
}

enum class LoadingState {
    Loading,
    Idle
}