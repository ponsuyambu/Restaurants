buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("challenge.plugins:gradle-plugin")
    }
}
// https://github.com/mockk/mockk/issues/281
allprojects {
    configurations.all {
        resolutionStrategy {
            force("org.objenesis:objenesis:2.6")
        }
    }
}
