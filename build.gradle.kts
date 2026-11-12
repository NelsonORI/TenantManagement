plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false // or whatever was originally here

    // Safe Args: generates classes for passing data between screens
    id("androidx.navigation.safeargs.kotlin") version "2.9.8" apply false
    // KSP generates Room's database code while building
    id("com.google.devtools.ksp") version "2.1.10-1.0.31" apply false
}