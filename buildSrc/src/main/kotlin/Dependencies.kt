object Dependencies {

    /// Android Core
    const val androidXCoreVersion = "1.7.0"
    const val appCompatVersion = "1.4.0"
    const val androidMaterialVersion = "1.4.0"
    const val androidXLifeCycleKtxVersion = "2.4.0"

    const val androidXCore = "androidx.core:core-ktx:$androidXCoreVersion"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
    const val androidMaterial = "com.google.android.material:material:$androidMaterialVersion"
    const val androidXLifeCycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$androidXLifeCycleKtxVersion"

    /// Compose
    const val composeVersion = "1.1.0"
    const val composeConstraintLayoutVersion = "1.0.0"
    const val composeActivitiesVersion = "1.4.0"
    const val composeViewModelsVersion = "2.4.0"
    const val accompanistInsetsVersion = "0.22.0-rc"
    const val landscapistGlideVersion = "1.4.5"

    const val composeUI = "androidx.compose.ui:ui:$composeVersion" // Tooling support (Previews, etc.)
    const val composeUITooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val composeFoundation = "androidx.compose.foundation:foundation:$composeVersion" // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    const val composeMaterialDesign = "androidx.compose.material:material:$composeVersion" // Material Design
    const val composeMaterialDesignIcons = "androidx.compose.material:material-icons-core:$composeVersion" // Material design icons
    const val composeMaterialDesignIconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion"
    const val composeActivities = "androidx.activity:activity-compose:$composeActivitiesVersion" // Integration with activities
    const val composeViewModels = "androidx.lifecycle:lifecycle-viewmodel-compose:$composeViewModelsVersion" // Integration with ViewModels
    const val composeRuntimeLiveData = "androidx.compose.runtime:runtime-livedata:$composeVersion" // Integration with observables
    const val composeRuntimeRxJava = "androidx.compose.runtime:runtime-rxjava2:$composeVersion"
    const val composeConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:$composeConstraintLayoutVersion"
    const val composeUITesting = "androidx.compose.ui:ui-test-junit4:$composeVersion" // UI Tests


    /// Jetpack DataStore - preferences maybe for later use
    const val dataStoreVersion = "1.0.0"
    const val protobufVersion = "3.18.0"

    const val dataStore = "androidx.datastore:datastore:$dataStoreVersion"
    const val dataStoreCore = "androidx.datastore:datastore-core:$dataStoreVersion"
    const val dataStorePreferences = "androidx.datastore:datastore-preferences:$dataStoreVersion"
    const val dataStorePreferencesCore = "androidx.datastore:datastore-preferences-core:$dataStoreVersion"
    const val protobuf = "com.google.protobuf:protobuf-javalite:$protobufVersion"

    /// Accompanist
    const val accompanistVersion = "0.20.3"

    const val accompanistSystemUIController = "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion"
    const val accompanistInsets = "com.google.accompanist:accompanist-insets:$accompanistInsetsVersion"
    const val accompanistInsetsUi = "com.google.accompanist:accompanist-insets-ui:$accompanistInsetsVersion"

    /// Landscapist Glide
    const val landscapistGlide = "com.github.skydoves:landscapist-glide:$landscapistGlideVersion"

    /// Navigation
    const val navigationComposeVersion = "2.4.0-rc01"

    const val navigationCompose = "androidx.navigation:navigation-compose:$navigationComposeVersion"

    /// Hilt DI
    const val hiltVersion = "2.38.1"

    const val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:$hiltVersion"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"

    /// Coroutines
    const val coroutinesAndroidVersion = "1.5.2"
    const val coroutinesPlayServicesVersion = "1.1.1"

    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesAndroidVersion"
    const val coroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesPlayServicesVersion"
}
