### Wisnu Android Foundation

I copy this code into all apps I make. I'm tired of doing it. Now it's a library.

### Integration

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    val foundation_version = "0.1.1"

    implementation "com.github.wisnukurniawan.foundation:core-analytics:$foundation_version"
    implementation "com.github.wisnukurniawan.foundation:lib-analytics-manager:$foundation_version"

    implementation "com.github.wisnukurniawan.foundation:core-json:$foundation_version"

    implementation "com.github.wisnukurniawan.foundation:core-loggr:$foundation_version"
    implementation "com.github.wisnukurniawan.foundation:lib-lifecycle-loggr:$foundation_version"

    implementation "com.github.wisnukurniawan.foundation:core-ui:$foundation_version"

    implementation "com.github.wisnukurniawan.foundation:core-viewmodel:$foundation_version"

    // Require desugar enabled in compile options -> coreLibraryDesugaringEnabled = true
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
    implementation "com.github.wisnukurniawan.foundation:core-date-time:$foundation_version"

    debugImplementation "com.github.wisnukurniawan.foundation:test-debug:$foundation_version"
    releaseImplementation "com.github.wisnukurniawan.foundation:test-debug-no-op:$foundation_version"
}
```
