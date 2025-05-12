rootProject.name = "playground"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
  repositories {
    mavenCentral()
    mavenLocal()
  }

  versionCatalogs {
    create("libs") {
      // http server
      version("undertow", "2.3.18.Final")
      library("undertow-core", "io.undertow", "undertow-core").versionRef("undertow")
      library("undertow-servlet", "io.undertow", "undertow-servlet").versionRef("undertow")
      library("jakarta.servlet.api", "jakarta.servlet", "jakarta.servlet-api").version("6.1.0")

      // spring
      version("spring", "6.2.4")
      library("spring-webmvc", "org.springframework", "spring-webmvc").versionRef("spring")
      library("spring-test", "org.springframework", "spring-test").versionRef("spring")

      // serialization
      version("kotlinx-serialization", "1.8.0")
      library("kotlinx-serialization-json", "org.jetbrains.kotlinx", "kotlinx-serialization-json")
        .versionRef("kotlinx-serialization")
      library("kaml", "com.charleskorn.kaml", "kaml").version("0.72.0")
    }
  }
}

include(":playground-apiserver")
