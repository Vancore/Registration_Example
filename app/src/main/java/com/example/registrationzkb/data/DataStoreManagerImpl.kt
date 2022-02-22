package com.example.registrationzkb.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map

const val REGISTRATION_DATASTORE = "REGISTRATION_DATASTORE"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = REGISTRATION_DATASTORE)

class DataStoreManagerImpl(private val context: Context) : DataStoreManager {

    companion object {
        val NAME = stringPreferencesKey("NAME")
        val EMAIL = stringPreferencesKey("EMAIL")
        val BIRTHDAY = longPreferencesKey("BIRTHDAY")
    }

    override suspend fun saveUserInput(registrationInput: RegistrationInput) {
        context.dataStore.edit { preferences ->
            preferences[NAME] = registrationInput.name
            preferences[EMAIL] = registrationInput.email
            preferences[BIRTHDAY] = registrationInput.birthday
        }
    }

    override suspend fun loadUserInput() = context.dataStore.data.map { preferences ->
        RegistrationInput(
            name = preferences[NAME]!!,
            email = preferences[EMAIL]!!,
            birthday = preferences[BIRTHDAY]!!
        )
    }
}