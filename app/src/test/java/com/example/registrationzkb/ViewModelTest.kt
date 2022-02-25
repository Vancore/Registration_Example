package com.example.registrationzkb

import com.example.registrationzkb.data.DataStoreManager
import com.example.registrationzkb.screens.registration.RegistrationViewModel
import com.example.registrationzkb.screens.success.InputLoadingState
import com.example.registrationzkb.screens.success.SuccessViewModel
import com.example.registrationzkb.usecases.ValidationState
import com.example.registrationzkb.usecases.ValidationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class ViewModelTest {

    private val registrationInput = RegistrationInputCreator.getValidRegistrationInput()
    private val dataStoreManager: DataStoreManager = Mockito.mock(DataStoreManager::class.java)
    private val validationUseCase = ValidationUseCase()

    private val registrationViewModel = RegistrationViewModel(
        dataStoreManager,
        validationUseCase
    )

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setup() {
        registrationViewModel.saveInputLocally(registrationInput)
    }

    @Test
    fun `when input is validated, receive correct validation state after`() {
        validationUseCase.validateInput(registrationInput)
        var validationState = ValidationState()

        testScope.launch {
            registrationViewModel.validationState.collect {
                validationState = it
            }
        }

        assertEquals(registrationInput.name, validationState.nameInput)
        assertEquals(registrationInput.email, validationState.emailInput)
        assertEquals(registrationInput.birthday, validationState.dateInput)
    }
}