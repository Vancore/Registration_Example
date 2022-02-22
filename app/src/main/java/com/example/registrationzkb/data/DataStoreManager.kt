package com.example.registrationzkb.data

import kotlinx.coroutines.flow.Flow

interface DataStoreManager {
    suspend fun saveUserInput(registrationInput: RegistrationInput)
    suspend fun loadUserInput(): Flow<RegistrationInput>
}