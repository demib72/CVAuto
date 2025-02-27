import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.0"
    id("org.jetbrains.dokka") version "1.7.20"
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.dokka")

    group = "com.waicool20.cvauto"
    version = "1.0-SNAPSHOT"

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        val versions = object {
            val KotlinCoroutines = "1.6.4"
        }

        implementation(kotlin("stdlib"))
        implementation(kotlin("reflect"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.KotlinCoroutines}")

        // Tests
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    }

    tasks {
        withType<KotlinCompile>().configureEach {
            compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
}

task<Zip>("docs") {
    dependsOn("dokkaHtmlCollector")
    destinationDirectory.set(file("$buildDir/dokka"))
    from(file("$buildDir/dokka/htmlCollector"))
    into("docs")
    archiveFileName.set("docs.zip")
}
