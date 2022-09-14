# NASASearchApp

* The application displays a list of Milky Way images taken in 2017 from NASA Search API.
* Built with Kotlin (Android Native)


# Major Features
* **Multi-module**: The app contains two modules that are reusable and can be used by other apps
* **Hilt**: The app uses Hilt for dependency injection to create dependencies and improve code reusability
* **Clean architecture**: The app follows a clean architectural pattern to improve code decoupling and testing
* **Data Binding**: The app uses data binding to bind views and layouts to reduce boilerplate code
* **Navigation Component**: The app uses a navigation component to connect between fragments
* **Retrofit**: The app uses retrofit to create network HTTP request and response
* **Kotlin flows and coroutine**: The app uses flow to perform asynchronous tasks
* **Glide**: The app uses glide for image loading
* **Unit Test**: The app uses Mockito and Turbine for unit testing and Truth for the assertion
* **UItest**: The app uses espresso for UI test and Truth for assertion


# Project Structure

The app has the following structure
* Module
  * **apimodule**: This module contains the code necessary for creating the Milky Way images request
     * common: This package is responsible for files that are used across the module
     * data: This package contains files related to data
     * di: This package contains files responsible for dependencies injection
     * domain: This package is responsible for complex  and simple business logic that is reusable
     * unitTest: This package is responsible for unit test for Mikly Images Api implementation
  * **app**: This module is responsible for the UI implementation of the application
     * common: This package is responsible for files that are used across the module
     * presentation: This package is responsible for the UI logic and implementation
     * androidTest: This package is responsible for UI test of fragments and navigations
*Gradle Scripts
   * These scripts are responsible for dependencies and plugins necessary for the project


# How to start the application

1. Download Android Studio

2. Clone or fork the repository by running the command below on your terminal

```
https://github.com/Gabrielhephzibah/NASASearchApp.git

```
       
3. Import or open the project on Android Studio

4. Open build.gradle in Android Studio and sync

5. Run the app on an emulator or directly on an Android device.


# Test
 * The app contains basic unit-test cases in the apimodule and android-test cases in the appmodule to measure the performance of the app.


# Todos
 * create a separate module for dependencies
 * add more test cases to have a wider test coverage
 * create UI reusable components
 * keep updating and maintaining the application for better performance

