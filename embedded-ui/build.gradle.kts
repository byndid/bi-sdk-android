import checks.ktlintCheckConfig
import config.configureAndroidLib
import config.configureMavenPublish
import utils.getProp

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
    id("org.jlleitschuh.gradle.ktlint")
    id("kotlin-parcelize")
    id("org.jetbrains.dokka")
}

configureAndroidLib()

// TODO: Move into a function like `configureMavenPublish`
tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
    dokkaSourceSets {
        named("main") {
            moduleName.set("Embedded UI SDK")
        }
    }
}

ktlintCheckConfig()

configureMavenPublish(
    groupIdForLib = "com.beyondidentity.android.sdk",
    artifactIdForLib = "embedded-ui"
)

val biSdkVersion = getProp("BUILD_CONFIG_BI_SDK_VERSION")

dependencies {
    implementation(project(":embedded"))
    implementation(Libs.KOTLIN_STD_LIB)
    implementation(Libs.KOTLIN_REFLECTION)
    implementation(Libs.KOTLINX_SERIALIZATION)
    implementation(Libs.ANDROIDX_PREFERENCES)
    implementation(Libs.ANDROIDX_APPCOMAPT)
    implementation(Libs.MATERIAL)
    implementation(Libs.CODE_SCANNER)
}