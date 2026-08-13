plugins {
    id("com.android.library")
}

android {
    namespace = "com.lladlam.melox.hiddenapi"
    compileSdk = 37

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
