package com.example.registrationzkb.screens.registration

import android.content.res.Configuration
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.registrationzkb.data.RegistrationInput
import com.example.registrationzkb.screens.shared.DefaultTopBar
import com.example.registrationzkb.screens.shared.ZKBCalendarView
import com.example.registrationzkb.ui.theme.RegistrationZKBTheme
import com.example.registrationzkb.utils.Utils
import com.example.registrationzkb.utils.Utils.Companion.convertDateToString
import com.example.registrationzkb.utils.Utils.Companion.convertLongDateToString
import com.example.registrationzkb.utils.Utils.Companion.specificDateToCalendar
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun RegistrationScreen(
    registrationViewModel: RegistrationViewModel = hiltViewModel(),
    validationSuccess: () -> Unit
) {

    val validationState by registrationViewModel.validationState.collectAsState()

    if (!validationState.inputIsValid) {
        ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
            Scaffold(topBar = { DefaultTopBar(title = "Registrierung") }) {
                RegistrationScreenContent(
                    errorInName = !validationState.nameInputValid || validationState.inputIsValid,
                    errorInEmail = !validationState.emailInputValid || validationState.inputIsValid,
                    errorInDate = !validationState.dateInputValid || validationState.inputIsValid,
                    validateInput = {
                        registrationViewModel.validateInput(it)
                    }
                )
            }
        }
    } else {
        registrationViewModel.saveInputLocally(
            RegistrationInput(
                validationState.nameInput,
                validationState.emailInput,
                validationState.dateInput
            )
        )
        DisposableEffect(LocalLifecycleOwner.current) {
            validationSuccess()
            onDispose { registrationViewModel.clearInput() }
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

    var selectedDate by remember { mutableStateOf(Utils.maximumCalendarTime()) }
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
            label = { Text("Ihr Name") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )
        if (showErrors && errorInName) {
            Text(
                text = "Das Namensfeld darf nicht leer sein",
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
                text = "Bitte geben Sie eine valide Email an",
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Box {
            TextField(
                value = convertDateToString(selectedDate),
                onValueChange = birthdayChange,
                label = { Text("Geburtstag") },
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
                text = "Bite geben sie einen g??ltigen Geburtstag an",
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
                    ZKBCalendarView(
                        lastPickedDate = selectedDate,
                        datePicked = { year, month, day ->
                            val date = specificDateToCalendar(year, month, day)
                            birthdayChange(convertLongDateToString(date.time))
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
            Text(text = "Registrieren")
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
        Scaffold(topBar = { DefaultTopBar(title = "Registrierung") }) {
            RegistrationScreenContent(
                errorInName = false,
                errorInEmail = true,
                errorInDate = false,
                validateInput = {}
            )
        }
    }
}