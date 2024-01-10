plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.runmate"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.runmate"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    buildTypes.configureEach {
        buildConfigField("String", "TOMTOM_API_KEY", "\"${project.findProperty("tomtomApiKey")}\"")
    }

    packagingOptions {
        jniLibs.pickFirsts.add("lib/**/libc++_shared.so")
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // ViewBinding
    implementation("com.github.kirich1409:viewbindingpropertydelegate-full:1.5.9")

    val version = "0.43.0"
    implementation("com.tomtom.sdk.navigation:navigation-online:$version")
    implementation("com.tomtom.sdk.maps:map-display:0.43.0")

    implementation("com.tomtom.sdk.location:provider-android:0.43.0")
    implementation("com.tomtom.sdk.location:provider-gms:0.43.0")
    implementation("com.tomtom.sdk.location:provider-proxy:0.43.0")
    implementation("com.tomtom.sdk.location:provider-api:0.43.0")

    implementation("com.tomtom.sdk.routing:route-planner-online:0.43.0")

    implementation("com.tomtom.sdk.location:provider-simulation:$version")
    implementation("com.tomtom.sdk.navigation:navigation-online:$version")
    implementation("com.tomtom.sdk.navigation:route-replanner-online:$version")
    implementation("com.tomtom.sdk.routing:route-planner-online:$version")

    // Navigation
    val navigation = "2.7.6"
    implementation("androidx.navigation:navigation-fragment-ktx:$navigation")
    implementation("androidx.navigation:navigation-ui-ktx:$navigation")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$navigation")

    // Dagger 2
    val dagger = "2.49"
    implementation("com.google.dagger:dagger:$dagger")
    kapt("com.google.dagger:dagger-compiler:$dagger")

    // Room DB
    val room = "2.6.1"
    implementation("androidx.room:room-ktx:$room")
    ksp("androidx.room:room-compiler:$room")
}