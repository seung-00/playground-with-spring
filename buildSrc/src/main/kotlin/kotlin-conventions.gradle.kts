plugins {
    idea
    kotlin("jvm")
    kotlin("plugin.serialization")
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("failed")
        setExceptionFormat("full")
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}

configurations.all {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    exclude(group = "org.slf4j", module = "slf4j-log4j12")
    exclude(group = "log4j", module = "log4j")
    exclude(group = "commons-logging", module = "commons-logging")
}

configurations {
    testImplementation.get().apply {
        extendsFrom(compileOnly.get())
    }
}
