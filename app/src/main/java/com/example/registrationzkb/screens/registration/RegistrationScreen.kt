package com.example.registrationzkb.screens.registration

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.registrationzkb.RegistrationViewModel
import com.example.registrationzkb.data.RegistrationInput
import com.example.registrationzkb.ui.theme.RegistrationZKBTheme
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun RegistrationScreen(
    registrationViewModel: RegistrationViewModel,
    validationSuccess: (String) -> Unit
) {

    val validationState by registrationViewModel.validationState.collectAsState()
    if (validationState.inputIsValid) {
        validationSuccess("something to pass") // ToDo: Save Inputs
    }

    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        Scaffold(topBar = { RegistrationTopBar(title = "Registration") }) {
            RegistrationScreenContent(
                errorInName = !validationState.nameInputValid,
                errorInEmail = !validationState.emailInputValid,
                errorInDate = !validationState.dateInputValid
            ) { input ->
                registrationViewModel.validateInput(input)
            }
        }
    }
}

@Composable
fun RegistrationScreenContent(
    errorInName: Boolean,
    errorInEmail: Boolean,
    errorInDate: Boolean,
    validateInput: (RegistrationInput) -> Unit
) {
    val (currentNameInput, nameChange) = remember { mutableStateOf("") }
    val (currentEmailInput, emailChange) = remember { mutableStateOf("") }
    val (currentBirthdayInput, birthdayChange) = remember { mutableStateOf(0L) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.padding(16.dp))

        TextField(
            value = currentNameInput,
            onValueChange = nameChange,
            label = { Text("Your Name") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )
        if (errorInName) {
            Text(
                text = "The Name input must not be empty",
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))

        TextField(
            value = currentEmailInput,
            onValueChange = emailChange,
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        if (errorInEmail) {
            Text(
                text = "Please provide a valid Email Address",
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Button(onClick = {
            validateInput(
                RegistrationInput(
                    currentNameInput,
                    currentEmailInput,
                    currentBirthdayInput
                )
            )
        }) {
            Text(text = "Register")
        }
    }
}

@Preview(
    name = "Registration Top Bar - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Preview(
    name = "Registration Top Bar - Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun RegistrationScreenPreview() {
    RegistrationZKBTheme {
        Scaffold(topBar = { RegistrationTopBar(title = "Registration") }) {
            RegistrationScreenContent(
                errorInName = false,
                errorInEmail = true,
                errorInDate = false,
                validateInput = {}
            )
        }
    }
}