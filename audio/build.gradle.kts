plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}
android {
    compileSdkVersion(28)

    defaultConfig {
        versionCode = 1
        versionName = "1.0"

        minSdkVersion(21)
        targetSdkVersion(28)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isUseProguard = false // user R8 instead
        }
    }

}

dependencies {

    /** Kotlin  */
    implementation(Libraries.kotlin)

    /** AndroidX media */
    implementation(Libraries.media)

    /** Glide */
    implementation(Libraries.glide)

    implementation("com.google.code.gson:gson:2.8.5")

    /** ExoPlayer */
    if (findProject(":exoplayer-library-core") != null) {
        api(project(":exoplayer-library-core"))
        api(project(":exoplayer-library-ui"))
        api(project(":exoplayer-extension-mediasession"))
    } else {
        api("com.google.android.exoplayer:exoplayer-core:2.9.1")
        api("com.google.android.exoplayer:exoplayer-ui:2.9.1")
        api("com.google.android.exoplayer:extension-mediasession:2.9.1")
    }


    // Testing
    androidTestImplementation("androidx.test:runner:1.1.1")
}
