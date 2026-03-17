# FeedExplorer
Code Challenge - FeedExplorer

A **technical code challenge** demonstrating modern Android development best practices with **Clean Architecture**, **Jetpack Compose**, and a fully reactive data pipeline.

This follows the architectural pattern suggested by Google. Separating the code in 3 different layers (Data, Domain, UI). Using retrofit to call the API, the navigation component to navigate within the app, compose to create the UI, Hilt for dependency injection, JUnit for unit testing.

## Technologies
 - Jetpack Compose
 - Coroutines
 - Navigation component
 - ViewModels
 - Retrofit
 - JUnit
 - Hilt

## Building & Running

### Prerequisites

- **Android Studio** Ladybug (2024.2.1) or later
- **JDK 17**
- **Android SDK 35**

### Build

```bash
# Debug build
./gradlew assembleDebug

# Run unit tests
./gradlew test

# Install on connected device
./gradlew installDebug
```
