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
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.21")

    implementation("androidx.media:media:1.1.0-alpha02")

    implementation("com.google.code.gson:gson:2.8.5")

    // ExoPlayer dependencies

    // This allows UAMP to utilize a local version of ExoPlayer, which is particularly
    // useful for extending the MediaSession extension, as well as for testing and
    // customization. If the ":exoplayer-library-core" project is included, we assume
    // the others are included as well.
    if (findProject(":exoplayer-library-core") != null) {
        api(project(":exoplayer-library-core"))
        api(project(":exoplayer-library-ui"))
        api(project(":exoplayer-extension-mediasession"))
    } else {
        api("com.google.android.exoplayer:exoplayer-core:2.9.1")
        api("com.google.android.exoplayer:exoplayer-ui:2.9.1")
        api("com.google.android.exoplayer:extension-mediasession:2.9.1")
    }

    // Glide dependencies
    api("com.github.bumptech.glide:glide:4.9.0")

    // Testing
    androidTestImplementation("androidx.test:runner:1.1.1")
}
