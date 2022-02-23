package com.example.registrationzkb.screens.success

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.registrationzkb.screens.shared.DefaultTopBar
import com.example.registrationzkb.ui.theme.RegistrationZKBTheme
import com.example.registrationzkb.utils.Utils
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun SuccessScreen(
    successViewModel: SuccessViewModel
) {

    val loadInputState by remember { successViewModel.registrationInput }.collectAsState()

    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        Scaffold(topBar = { DefaultTopBar(title = "Registration Data") }) {
            val registrationInput = loadInputState.input
            SuccessScreenContent(
                name = registrationInput.name,
                email = registrationInput.email,
                birthday = Utils.convertDateToString(registrationInput.birthday)
            )
        }
    }
}

@Composable
fun SuccessScreenContent(
    name: String,
    email: String,
    birthday: String
) {
    Column {
        Text(name)
        Text(email)
        Text(birthday)
    }
}

@Preview(
    name = "Success Screen Preview - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Preview(
    name = "Success Screen Preview - Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun SuccessScreenPreview() {
    RegistrationZKBTheme {
        SuccessScreenContent(
            name = "Name Preview",
            email = "Email Preview",
            birthday = "Birthday Preview"
        )
    }
}