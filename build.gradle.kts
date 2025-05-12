allprojects {
  group = "playground"
  version = property("version") as String
}

subprojects {
  apply(plugin = "kotlin-conventions")
}
