
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "rmg.prsolution.dfm.next"
        versionCode = 1
        versionName = "1.0"

        minSdkVersion(21)
        targetSdkVersion(28)

        compileOptions {
            setSourceCompatibility(1.8)
            setTargetCompatibility(1.8)
        }
    }
    buildTypes {
        getByName("debug") {
            buildConfigField("String", "PROD_URL", Constants.PROD_URL)
            buildConfigField("String", "DEV_URL", Constants.DEV_URL)
        }
        getByName("release") {
            isMinifyEnabled = false
            isUseProguard = false // user R8 instead
        }

    }
}

dependencies {
    implementation(project(":audio"))

    /** Kotlin  */
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.21")

    /** AndroidX */
    implementation("androidx.appcompat:appcompat:1.1.0-alpha03")
    implementation("androidx.core:core-ktx:1.1.0-alpha05")

    implementation("androidx.recyclerview:recyclerview:1.0.0")

    /** AndroidX media */
    implementation("androidx.media:media:1.1.0-alpha03")

    /** AndroidX Constraint Layout */
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")

    /** AndroidX LifeCycle extention */
    implementation("androidx.lifecycle:lifecycle-extensions:2.0.0")

    // Glide dependencies
    implementation("com.github.bumptech.glide:glide:4.9.0")
}
