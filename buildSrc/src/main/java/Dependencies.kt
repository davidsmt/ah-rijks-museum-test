object ApplicationParams {
    const val id = "com.ahrijksmuseum.app"
}

object Modules {
    const val app = ":app"
    const val view = ":view"
    const val presentation = ":presentation"
    const val domain = ":domain"
    const val data = ":data"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val gradle = "7.0.2"

    const val buildTool = "30.0.2"
    const val compileSdk = 31
    const val minSdk = 21
    const val targetSdk = 31

    const val kotlin = "1.6.0"
    const val ktx = "1.7.0"

    const val dagger = "2.37"

    const val retrofit = "2.9.0"
    const val moshi = "1.13.0"
    const val loggingInterceptor = "4.4.0"

    const val gson = "2.8.9"

    const val room = "2.4.2"

    const val paging = "3.1.0"

    const val coroutines = "1.4.1"

    const val fragment = "1.3.5"
    const val navigation = "2.3.5"
    const val lifecycle = "2.3.1"

    const val appcompat = "1.3.0"
    const val design = "1.4.0-rc01"
    const val cardview = "1.0.0"
    const val recyclerview = "1.2.1"
    const val constraintLayout = "2.0.4"
    const val swipeRefreshLayout = "1.1.0"

    const val glide = "4.12.0"

    const val junit = "4.13"
    const val assertjCore = "3.15.0"
    const val mockk = "1.12.1"
}

object Plugins {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object Libraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomPaging = "androidx.room:room-paging:${Versions.room}"

    const val pagingCommonKtx = "androidx.paging:paging-common-ktx:${Versions.paging}"
    const val pagingRuntimeKtx = "androidx.paging:paging-runtime-ktx:${Versions.paging}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    const val fragmentKtx =
        "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationRuntime =
        "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object SupportLibraries {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val appcompatResources = "androidx.appcompat:appcompat-resources:${Versions.appcompat}"
    const val design = "com.google.android.material:material:${Versions.design}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
}

object TestLibraries {
    const val junit = "junit:junit:${Versions.junit}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    const val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
}