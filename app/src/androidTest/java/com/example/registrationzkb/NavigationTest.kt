package com.example.registrationzkb

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.registrationzkb.screens.registration.RegistrationScreen
import com.example.registrationzkb.ui.theme.RegistrationZKBTheme
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun app_launch() {
        // Start the app
        composeTestRule.setContent {
            RegistrationZKBTheme {
                RegistrationScreen{}
            }
        }

        composeTestRule.onNodeWithText("Registrieren").performClick()

        composeTestRule.onNodeWithText("Das Namensfeld darf nicht leer sein").assertIsDisplayed()
    }

}