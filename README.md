# RunTracker

RunTracker is an Android application designed to track users' runs and save their data. The app supports both Android phones and Wear OS devices, with the Wear OS app working in conjunction with the phone app. It features a modular architecture adhering to Clean Architecture principles and employs modern Android development practices.

## Overview

<table>
  <tr>
    <td><img src="gifs/phone.gif" alt="App on Phone" width="214"></td>
    <td><img src="gifs/watch.gif" alt="App on Watch" width="480"></td>
  </tr>
  <tr>
    <td style="text-align: center;">App on Phone</td>
    <td style="text-align: center;">App on Watch</td>
  </tr>
</table>


## Features

- **Run Tracking**: Tracks runs, including location, time, and distance.
- **Wear OS Support**: Companion app for Wear OS watches to work seamlessly with the phone app.
- **Dynamic Features**: Analytics module as an optional feature, offering insights like total distance, total time, and fastest runs.
- **Offline and Online Data Sync**:
    - Firebase Firestore and Firebase Storage for remote data and pictures.
    - Ktor and HttpClientFactory for remote data handling with a custom server when enabled.
    - Room database for local storage with offline-first synchronization using WorkManager, integrating seamlessly with the custom server for data synchronization when enabled.
- **Authentication**:
    - Firebase Authentication for user login and registration.
    - Optional custom server authentication using Ktor and HttpClient.
- **Real-Time Notifications**: Displays run details (e.g., elapsed time) even when the app is in the background.
- **Connectivity**: Syncs phone and watch data using a custom connectivity module.

## Architecture

RunTracker is implemented using a multi-module architecture. Each module follows Clean Architecture principles, including separate `data`, `domain`, and `presentation` layers.

### Key Architectural Features

- **MVI Pattern**: Ensures a clear separation of concerns and unidirectional data flow.
- **Dependency Injection**: Managed using Koin for easier and scalable dependency management.
- **Jetpack Compose**: Used for building a modern, native Android UI.
- **Dynamic Features**: Allows users to install specific parts of the app (e.g., Analytics) after the main installation.
- **Build Logic Module**: Centralized configuration for build types, dependencies, and convention plugins.

## Modules

### Core Module
The core module provides shared functionalities and dependencies for other modules:

- **Connectivity Module**: Enables communication between phone and Wear OS watch.
- **Database Module**: Includes Room database for local data storage.
- **Data Module**: Implements an `OfflineFirstRunRepository` to manage local and remote data synchronization.
- **Notification Module**: Contains the `Service` class for background run tracking and real-time notifications.
- **Presentation Module**: Provides design systems for phones and watches, including colors, fonts, and themes.

### Run Module
Handles all phone-specific functionalities:

- Screens and ViewModels.
- Network implementations for saving data remotely.
- Running tracker and location observer.
- Watch connector for syncing data with the Wear OS app.

### Wear Module
Contains all necessary implementations for the Wear OS app:

- UI and data handling for the watch app.
- Communication with the phone app.

### Analytics Module
A dynamic feature module offering advanced run analytics:

- Total distance, total time, fastest run, etc.
- Installable after the main app installation.

### Build Logic Module
Centralized build logic:

- Convention plugins for common tasks.
- Simplified build.gradle files.
- Centralized dependency version management.

## Technologies Used

- **Jetpack Compose**: Modern UI toolkit for building native Android interfaces.
- **Ktor**: For network communication with optional custom server.
- **Firebase**: Firestore, Storage, and Authentication for remote data handling.
- **Room Database**: Local data storage.
- **WorkManager**: Ensures reliable background tasks.
- **Koin**: Lightweight dependency injection framework.
- **Kotlin Flow and Coroutines**: For asynchronous programming and reactive streams.

## Getting Started

### Prerequisites
- Android Studio Flamingo or newer.
- JDK 11 or higher.
- An Android device or emulator running Android 6.0 (API level 23) or higher.

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Stan-Balabushevich/RunTrack.git
   ```
2. Open the project in Android Studio.
3. In local.properties file create MAPS_API_KEY=GoogleMapsApiKey - change for your api key 
4. Build the project and install the app on your device.

### Dynamic Feature Installation
The Analytics module can be installed from within the app as needed.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

---

Feel free to contribute, report issues, or suggest enhancements!
