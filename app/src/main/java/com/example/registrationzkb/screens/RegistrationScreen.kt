package com.example.registrationzkb.screens

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.registrationzkb.RegistrationViewModel
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun RegistrationScreen(
    registrationViewModel: RegistrationViewModel,
    validationSuccess: (String) -> Unit
) {
    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        Scaffold(topBar = {
            Text(text = "Topbar Text")
        }) {
            RegistrationScreenContent(registrationViewModel.toString())
            registrationViewModel.doSomething()
        }
    }
}

@Composable
fun RegistrationScreenContent(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun RegistrationScreenPreview() {

}