package com.example.registrationzkb

enum class AppScreens {

    // Here we could pass different icons to the screen
    RegistrationForm,
    Success;
    // or another Screen, like 'SuccessScreen(),' ...

    companion object {
        // get Screen from Route
        fun fromRoute(route: String?): AppScreens =
            when (route?.substringBefore("/")) {
                RegistrationForm.name -> RegistrationForm
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}