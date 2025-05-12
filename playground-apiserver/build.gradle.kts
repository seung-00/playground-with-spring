import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  kotlin("jvm") version "2.1.0"
  id("org.jetbrains.kotlin.plugin.spring") version "2.1.0"
}

dependencies {
  implementation(libs.kotlinx.serialization.json)

  implementation(libs.spring.webmvc)
  implementation(libs.jakarta.servlet.api)
  implementation(libs.undertow.servlet)
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(24) // JVM 실행 환경 jdk 24
  }
  sourceCompatibility = JavaVersion.VERSION_21
  targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
  compilerOptions {
    jvmTarget.set(JvmTarget.JVM_21) // Kotlin 이 생성하는 .class 바이트코드 버전은 21 까지
    freeCompilerArgs.addAll("-Xjsr305=strict")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
