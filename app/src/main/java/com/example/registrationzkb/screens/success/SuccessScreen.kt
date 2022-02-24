package com.example.registrationzkb.screens.success

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.registrationzkb.screens.shared.DefaultTopBar
import com.example.registrationzkb.ui.theme.RegistrationZKBTheme
import com.example.registrationzkb.utils.Utils
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun SuccessScreen(
    successViewModel: SuccessViewModel = hiltViewModel()
) {

    val loadInputState by remember { successViewModel.registrationInput }.collectAsState()

    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        Scaffold(topBar = { DefaultTopBar(title = "Erfolg!") }) {
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
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.padding(8.dp))
        val styledTitle = buildAnnotatedString {
            append("Danke für Ihre Registrierung")
            append(AnnotatedString(text = "!", spanStyle = SpanStyle(MaterialTheme.colors.secondary)))
        }
        Text(text = styledTitle, style = MaterialTheme.typography.h3, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "Ihr Name:", style = MaterialTheme.typography.subtitle1, textAlign = TextAlign.Center)
        Text(text = name, style = MaterialTheme.typography.body1, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Ihre Email:", style = MaterialTheme.typography.subtitle1, textAlign = TextAlign.Center)
        Text(text = email, style = MaterialTheme.typography.body1, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Ihr Geburtsdatum:", style = MaterialTheme.typography.subtitle1, textAlign = TextAlign.Center)
        Text(text = birthday, style = MaterialTheme.typography.body1, textAlign = TextAlign.Center)
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
        Scaffold(topBar = { DefaultTopBar(title = "Registration Data") }) {
            SuccessScreenContent(
                name = "Name Preview",
                email = "Email Preview",
                birthday = "Birthday Preview"
            )
        }
    }
}