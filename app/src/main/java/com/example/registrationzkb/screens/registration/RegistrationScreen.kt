package com.example.registrationzkb.screens.registration

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.registrationzkb.AppScreens
import com.example.registrationzkb.data.RegistrationInput
import com.example.registrationzkb.screens.shared.DefaultTopBar
import com.example.registrationzkb.screens.shared.ZKBCalendarView
import com.example.registrationzkb.ui.theme.RegistrationZKBTheme
import com.example.registrationzkb.utils.Utils.Companion.convertDateToString
import com.example.registrationzkb.utils.Utils.Companion.specificDateToCalendar
import com.google.accompanist.insets.ProvideWindowInsets
import java.util.*

@Composable
fun RegistrationScreen(
    registrationViewModel: RegistrationViewModel,
    navHostController: NavHostController,
    validationSuccess: () -> Unit
) {

    val validationState by registrationViewModel.validationState.collectAsState()

    if (validationState.inputIsValid) {
        registrationViewModel.saveInputLocally(
            RegistrationInput(
                name = validationState.nameInput,
                email = validationState.emailInput,
                birthday = validationState.dateInput,
            )
        )
        LaunchedEffect("") {
            validationSuccess()
        }
    }

    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        Scaffold(topBar = { DefaultTopBar(title = "Registration") }) {
            RegistrationScreenContent(
                errorInName = !validationState.nameInputValid,
                errorInEmail = !validationState.emailInputValid,
                errorInDate = !validationState.dateInputValid,
                validateInput = registrationViewModel::validateInput
            )
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
    val (currentBirthdayInput, birthdayChange) = remember { mutableStateOf("") }

    var selectedDate by remember { mutableStateOf(Date()) }
    var showCalendar by remember { mutableStateOf(false) }
    var showErrors by remember { mutableStateOf(false) }

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
        if (showErrors && errorInName) {
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
            ),
            keyboardActions = KeyboardActions(
                onNext = { showCalendar = !showCalendar }
            )
        )
        if (showErrors && errorInEmail) {
            Text(
                text = "Please provide a valid Email Address",
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Box {
            TextField(
                value = currentBirthdayInput,
                onValueChange = birthdayChange,
                label = { Text("Birthday") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Go
                ),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.EditCalendar,
                        contentDescription = null
                    )
                },
                modifier = Modifier
                    .clickable {
                        showCalendar = !showCalendar
                    }
                    .padding(horizontal = 24.dp)
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0f)
                    .clickable(onClick = { showCalendar = !showCalendar }),
            )
        }
        if (showErrors && errorInDate) {
            Text(
                text = "Please provide a valid Date for your birthday",
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
            )
        }

        if (showCalendar) {
            Dialog(onDismissRequest = { showCalendar = !showCalendar }) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colors.background)
                        .border(BorderStroke(0.dp, Color.Transparent), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    ZKBCalendarView(datePicked = { year, month, day ->
                        val date = specificDateToCalendar(year, month, day)
                        birthdayChange(convertDateToString(date.time))
                        selectedDate = date
                        showCalendar = !showCalendar
                    })
                }
            }
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Button(onClick = {
            validateInput(
                RegistrationInput(
                    currentNameInput,
                    currentEmailInput,
                    selectedDate.time
                )
            )
            showErrors = true
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
        Scaffold(topBar = { DefaultTopBar(title = "Registration") }) {
            RegistrationScreenContent(
                errorInName = false,
                errorInEmail = true,
                errorInDate = false,
                validateInput = {}
            )
        }
    }
}