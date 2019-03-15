import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("com.github.ben-manes.versions") version "0.21.0" // uses gradle depUp ; show old dependencies in terminal
    id("org.jmailen.kotlinter") version "1.21.0"
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

        sourceSets {
            getByName("main").java.srcDirs("src/main/kotlin")
            getByName("debug").java.srcDirs("src/debug/kotlin")
            getByName("release").java.srcDirs("src/release/kotlin")
        }

        vectorDrawables.useSupportLibrary = true

        androidExtensions {
            isExperimental = true
        }

        versionCode =
                (AppVersion.majorAppVersion * 10_000) + (AppVersion.minorAppVersion * 1_000) + (AppVersion.patchAppVersion * 100)

        versionName = "${AppVersion.majorAppVersion}" +
                ".${AppVersion.minorAppVersion}" +
                ".${AppVersion.patchAppVersion}"


        applicationVariants.all(object : Action<ApplicationVariant> {
            override fun execute(variant: ApplicationVariant) {
                println("variant: $variant")
                variant.outputs.all(object : Action<BaseVariantOutput> {
                    override fun execute(output: BaseVariantOutput) {

                        val outputImpl = output as BaseVariantOutputImpl
                        val fileName = output.outputFileName
                                .replace("-release", "-release-v$versionName-vc$versionCode")
                                .replace("-debug", "-debug-v$versionName-vc$versionCode")
                        println("output file login: $fileName")
                        outputImpl.outputFileName = fileName
                    }
                })
            }
        })


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

kotlinter {
    ignoreFailures = false
    indentSize = 4
    continuationIndentSize = 4
}


dependencies {
    implementation(project(":audio"))

    /** Kotlin  */
    implementation(Libraries.kotlin)
    implementation(Libraries.anko)

    /**  Coroutines */
    implementation(Libraries.coroutinesKotlinAndroid)

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

    /** Networkin */

    // Retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitMock)
    implementation(Libraries.retrofitCoroutines)
    // OkHttp
    implementation(Libraries.okhttp)
    implementation(Libraries.okhttpLoging)
    // Moshi
    implementation(Libraries.moshi)

    // Cache
    implementation(Libraries.layercache)
    implementation(Libraries.layercacheAndroid)
    implementation(Libraries.layercacheLiveData)

    /** DI */
    implementation(Libraries.koinAndroid)
    implementation( Libraries.koinViewModel)


    /** Glide */
    implementation(Libraries.glide)

    // Picasso
    implementation(Libraries.picasso)

    /** Debug Drawer */
    implementation(Libraries.debugDrawer)
    implementation(Libraries.debugDrawerLeak)
    implementation(Libraries.debugDrawerRetrofit)
    implementation(Libraries.debugDrawerOkHTTP)
    implementation(Libraries.debugDrawerTimber)

    /** Logs */
    // Timber
    implementation(Libraries.timber)
    // Chuck
    debugImplementation(Libraries.chuck)
    releaseImplementation(Libraries.chuckRelease)

    /** LeakCanary */
    debugImplementation(Libraries.leakCanaryAndroid)
    releaseImplementation(Libraries.leakCanaryAndroidNoOp)

    // Material Dialog
    implementation(Libraries.materialDialog)


    /** TEST */
    // MOCK WEB SERVER
    testImplementation("com.squareup.okhttp:mockwebserver:2.7.5")
    // CORE TESTING
    testImplementation("android.arch.core:core-testing:1.1.1")
    // ROBOLECTRIC
    testImplementation("org.robolectric:robolectric:4.2")
    //KOIN
    testImplementation("org.koin:koin-test:2.0.0-beta-1")
}
