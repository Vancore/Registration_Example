package com.example.registrationzkb

import androidx.lifecycle.ViewModel
import com.example.registrationzkb.data.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    fun doSomething() {
        //
    }
}