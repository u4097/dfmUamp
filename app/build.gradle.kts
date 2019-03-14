plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("com.github.ben-manes.versions") version "0.21.0" // uses gradle depUp ; show old dependencies in terminal
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
    implementation(Libraries.kotlin)

    /** AndroidX */
    implementation(Libraries.androidx)
    implementation(Libraries.ktx)

    /** AndroidX Constraint Layout */
    implementation(Libraries.constraintLayout)

    /** RecyclerView */
    implementation(Libraries.recyclerView)

    /** AndroidX media */
    implementation(Libraries.media)


    /** AndroidX LifeCycle extention */
    implementation(Libraries.lifecycle)

    /** Glide */
    implementation(Libraries.glide)
}
