object AppVersion {
    // App version information
    const val majorAppVersion = 0
    const val minorAppVersion = 1
    const val patchAppVersion = 0
    const val buildAppVersion = 0
}


object ApplicationId {
    const val id = "ru.prsolution.winstrike"
}

object Android {
    const val compileSdkVersion = 28
    const val minSdkVersion = 21
    const val targetSdkVersion = 28
}


object Versions {
    const val gradle = "3.5.0-alpha07"

    const val googleServicies = "4.2.0"
    const val kotlin = "1.3.21"
    const val anko = "0.10.8"
    const val androidx = "1.1.0-alpha02"
    const val lifecycle = "2.0.0"
    const val ktx = "1.1.0-alpha04"

    const val navigation = "2.0.0-rc02"

    const val constraintLayout = "2.0.0-alpha3"


    const val retrofit = "2.5.0"
    const val okhttp = "3.13.1"
    const val coroutinesRetrofit = "0.9.2"
    const val coroutines = "1.1.1"

    const val fresco = "1.13.0"
    const val lottie = "3.0.0-beta1"
    const val decoro = "1.3.5"
    const val showhidepasswordedittext = "0.8"

    const val chuck = "1.1.0"
    const val timber = "4.7.1"

    const val firebase = "16.0.7"
    const val firebaseMessaging = "17.3.4"
    const val fabric = "1.27.1"
    const val crashlytics = "2.9.8"

    const val leakCanary = "1.6.3"

    const val rxpaper = "1.4.0"

    const val koin = "2.0.0-beta-1"

    const val materialDialog = "2.0.3"

    const val materialDisign = "1.0.0"

    const val debugDrawer = "0.9.5"

    const val picasso = "2.71828"

    const val mockwebserver = "2.7.5"

    const val androidArchTest = "1.1.1"

    const val robolectric = "4.2"

    const val layercache = "0.4.0"

    const val mockito = "2.25.0"
}


object Libraries {

    // CORE
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val androidx = "androidx.appcompat:appcompat:${Versions.androidx}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val lifecycle = "android.arch.lifecycle:extensions:${Versions.lifecycle}"

    // NAVIGATION
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    // CONSTRAINT LAYOUT
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // KOIN
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    // RETROFIT
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    // MOSHI
    const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    // RETROFIT COROUTININES JAKEWHARTON
    const val retrofitCoroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutinesRetrofit}"
    // COROUTINES
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesKotlinAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // OKHTTP
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLoging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    // ANKO
    const val anko = "org.jetbrains.anko:anko:${Versions.anko}"

    // TIMBER
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // FIREBASE
    const val fireBase = "com.google.firebase:firebase-core:${Versions.firebase}"
    const val fireBaseMessaging = "com.google.firebase:firebase-messaging:${Versions.firebaseMessaging}"

    // CHUCK
    const val chuck = "com.readystatesoftware.chuck:library:${Versions.chuck}"
    const val chuckRelease = "com.readystatesoftware.chuck:library-no-op:${Versions.chuck}"

    // LEAK CANARY
    const val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    const val leakCanaryAndroidNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanary}"

    // PICASSO
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    // FRESCO
    const val fresco = "com.facebook.fresco:fresco:${Versions.fresco}"
    // MATERIAL DIALOG
    const val materialDialog = "com.afollestad.material-dialogs:core:${Versions.materialDialog}"


    // DEBUG DRAWER
    const val debugDrawer = "au.com.gridstone.debugdrawer:debugdrawer:${Versions.debugDrawer}"
    const val debugDrawerLeak = "au.com.gridstone.debugdrawer:debugdrawer-leakcanary:${Versions.debugDrawer}"
    const val debugDrawerRetrofit = "au.com.gridstone.debugdrawer:debugdrawer-retrofit:${Versions.debugDrawer}"
    const val debugDrawerTimber = "au.com.gridstone.debugdrawer:debugdrawer-timber:${Versions.debugDrawer}"
    const val debugDrawerOkHTTP = "au.com.gridstone.debugdrawer:debugdrawer-okhttp-logger:${Versions.debugDrawer}"


    // CACHE
    const val layercache = "com.appmattus:layercache:${Versions.layercache}"
    const val layercacheAndroid = "com.appmattus:layercache-android:${Versions.layercache}"
    const val layercacheLiveData = "com.appmattus:layercache-android-livedata:${Versions.layercache}"


    // CORE TESTING
    const val androidArchTest = "android.arch.core:core-testing:${Versions.androidArchTest}"

    // MOCKITO
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"

    // MOCK WEB SERVER
    const val mockwebserver = "com.squareup.okhttp:mockwebserver:${Versions.mockwebserver}"

    // ROBOLECTRIC
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    // KOTLIN TEST
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"

    // KOIN TEST
    const val koinTest = "org.koin:koin-test:${Versions.koin}"
}


// API URL
object Constants {
    const val DEV_URL = "\"http://185.244.173.11/v0/\""
    const val PROD_URL = "\"http://api.winstrike.ru:8000/api/v1/\""
}

object Modules {
    const val app = ":app"
}

