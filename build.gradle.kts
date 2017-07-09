import de.dynamicfiles.projects.gradle.plugins.javafx.JavaFXGradlePlugin
import de.dynamicfiles.projects.gradle.plugins.javafx.JavaFXGradlePluginExtension
import jp.co.kokou.junit5.ext.filters
import jp.co.kokou.junit5.ext.engines
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.junit.platform.gradle.plugin.*

val spek_version = "1.1.2"
val junit_version = "5.0.0-M4"

buildscript {

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.1.3")
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0-M4")
        classpath("de.dynamicfiles.projects.gradle.plugins:javafx-gradle-plugin:8.8.2")
    }
}


apply {
    plugin<KotlinPlatformJvmPlugin>()
    plugin<JUnitPlatformPlugin>()
    plugin<JavaPlugin>()
    plugin<JavaFXGradlePlugin>()
}

configure<JUnitPlatformExtension> {
    enableStandardTestTask = true

    filters {
        engines {
            include("junit-jupiter", "spek")
        }
    }
}

configure<JavaFXGradlePluginExtension> {
    isVerbose = true
    mainClass = "jp.co.kokou.sample.MainAppKt"
    jfxAppOutputDir = "build/jfx/app"
    jfxMainAppJarName = "${project.name}-jfx.jar"
    deployDir = "src/main/deploy"
    isUseEnvironmentRelativeExecutables = true
    libFolderName = "lib"

    // gradle jfxJar
    isCss2bin = false
    preLoader = null
    isUpdateExistingJar = false
    isAllPermissions = false
    manifestAttributes = mapOf<String, String>()
    isAddPackagerJar = true
    isCopyAdditionalAppResourcesToJar = false
    isSkipCopyingDependencies = false
    isUseLibFolderContentForManifestClasspath = false
    fixedManifestClasspath = null

    // gradle jfxNative
    // omit

    // gradle jfxRun
    runJavaParameter = null
    runAppParameter = null

    // per default the outcome of the gradle "jarTask" will be used,
    // set this to specify otherwise (like proguard-output)
    alternativePathToJarFile = null

    // to disable patching of ant-javafx.jar, set this to false
    isUsePatchedJFXAntLib = true

    // making it able to support absolute paths, defaults to "false" for maintaining old behaviour
    isCheckForAbsolutePaths = false

    // gradle jfxGenerateKeyStore
    keyStore = "src/main/deploy/keystore.jks"
    keyStoreAlias = "kokou"
    keyStorePassword = "XXXXX"
    keyPassword = null // will default to keyStorePassword
    keyStoreType = "jks"
    isOverwriteKeyStore = false

    certDomain = null // required
    certOrgUnit = null // defaults to "none"
    certOrg = null // required
    certState = null // required
    certCountry = null // required
}

repositories {
    mavenCentral()
}

dependencies {
    compile(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jre8")
    compile(group = "org.jetbrains.kotlin", name = "kotlin-reflect")

    testCompile(group = "org.junit.jupiter", name = "junit-jupiter-api", version = junit_version)
    testRuntime(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = junit_version)

    testCompile(group = "org.jetbrains.spek", name = "spek-api", version = spek_version)
    testCompile(group = "org.jetbrains.spek", name = "spek-data-driven-extension", version = spek_version)
    testCompile(group = "org.jetbrains.spek", name = "spek-subject-extension", version = spek_version)
    testRuntime(group = "org.jetbrains.spek", name = "spek-junit-platform-engine", version = spek_version)

    testCompile(group = "org.assertj", name = "assertj-core", version = "3.8.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        javaParameters = true
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

tasks.withType<Wrapper> {
    distributionType = Wrapper.DistributionType.ALL
    gradleVersion = "4.0.1"
}
