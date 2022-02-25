plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin ("android")
    kotlin("kapt")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "vancore.registrationzkb"
        minSdk = 26
        targetSdk =  31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Android Core
    implementation(Dependencies.androidXCore)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.androidMaterial)
    implementation(Dependencies.androidXLifeCycleKtx)
    implementation(Dependencies.androidXLifeCycle)

    // Jetpack Compose
    implementation(Dependencies.composeUI) // Tooling support (Previews, etc.)
    implementation(Dependencies.composeUITooling) // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation(Dependencies.composeFoundation) // Material Design
    implementation(Dependencies.composeMaterialDesign) // Material design icons
    implementation(Dependencies.composeMaterialDesignIcons)
    implementation(Dependencies.composeMaterialDesignIconsExtended) // Integration with activities
    implementation(Dependencies.composeActivities) // Integration with ViewModels
    implementation(Dependencies.composeViewModels) // Integration with observables
    implementation(Dependencies.composeRuntimeLiveData)
    implementation(Dependencies.composeRuntimeRxJava)
    implementation(Dependencies.composeConstraintLayout)

    // Accompanist
    implementation(Dependencies.accompanistSystemUIController)
    implementation(Dependencies.accompanistInsets)
    implementation(Dependencies.accompanistInsetsUi)

    // Landscapist Glide - Image Loading
    implementation(Dependencies.landscapistGlide)

    // Jetpack Navigation
    implementation(Dependencies.navigationCompose)

    // Hilt DI
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)

    // Coroutines
    implementation(Dependencies.coroutinesAndroid)
    implementation(Dependencies.coroutinesPlayServices)

    // Jetpack Datastore
    implementation(Dependencies.dataStore)
    implementation(Dependencies.dataStoreCore)
    implementation(Dependencies.dataStorePreferences)
    implementation(Dependencies.dataStorePreferencesCore)

    // tryout
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    /// Testing
    testImplementation(Dependencies.mockito)
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.kotlinxCoroutineTesting)
}
