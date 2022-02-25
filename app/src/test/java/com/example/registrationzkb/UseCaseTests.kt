package com.example.registrationzkb

import com.example.registrationzkb.usecases.ValidationState
import com.example.registrationzkb.usecases.ValidationUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Test
import org.junit.Assert.*

@ExperimentalCoroutinesApi
class UseCaseTests {

    private val invalidDateRegistrationInput = RegistrationInputCreator.getInvalidDateRegistrationInput()
    private val invalidEmailRegistrationInput = RegistrationInputCreator.getInvalidEmailRegistrationInput()
    private val invalidNameRegistrationInput = RegistrationInputCreator.getInvalidNameRegistrationInput()
    private val validRegistrationInput = RegistrationInputCreator.getValidRegistrationInput()
    private val validationUseCase = ValidationUseCase()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Test
    fun `when input is valid, inputIsValid is true`() {
        validationUseCase.validateInput(validRegistrationInput)
        var validationState = ValidationState()

        testScope.launch {
            validationUseCase.validationState.collect {
                validationState = it
            }
        }

        assertTrue(validationState.inputIsValid)
    }

    @Test
    fun `when date input is invalid, inputIsValid is false`() {
        validationUseCase.validateInput(invalidDateRegistrationInput)
        var validationState = ValidationState()

        testScope.launch {
            validationUseCase.validationState.collect {
                validationState = it
            }
        }

        assertFalse(validationState.inputIsValid)
    }

    @Test
    fun `when email input is invalid, inputIsValid is false`() {
        validationUseCase.validateInput(invalidEmailRegistrationInput)
        var validationState = ValidationState()

        testScope.launch {
            validationUseCase.validationState.collect {
                validationState = it
            }
        }

        assertFalse(validationState.inputIsValid)
    }

    @Test
    fun `when name input is invalid, inputIsValid is false`() {
        validationUseCase.validateInput(invalidNameRegistrationInput)
        var validationState = ValidationState()

        testScope.launch {
            validationUseCase.validationState.collect {
                validationState = it
            }
        }

        assertFalse(validationState.inputIsValid)
    }
}