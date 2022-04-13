# Booking-Sample-App


This app is built to demonstrate the modern design of an Android application


# About 


We load Rooms data from API and store it in a Room database (persistence storage)

The Rooms are always loaded from a local database. Remote and local data is always synchronised


The app is offline-capable

The app supports a dark theme


The API used in this app is statically hosted here https://novuyo.deeplytix.net/novuyo/all


# Tools


The application is built using the following tools


Kotlin - Official programming language for Android development

Android Architecture Components - Android Architecture Components are a part of Android Jetpack


 LiveData - Notify views of any database changes.
 Navigation - Handle everything needed for in-app navigation.
 ViewModel - Manage UI-related data in a lifecycle conscious way.
 ViewBinding -Declaratively bind UI elements in our layout to data sources of our app.
 Room -Fluent SQLite database access.


Coroutines - A very efficient and complete framework to manage concurrency in a more performant and simple way

Flow - A better way to handle the stream of data asynchronously that executes sequentially.

Dependency Injection

Hilt-Dagger

Retrofit - Android Networking library that reduces a lot of Boilerplate code and helps in consuming the web service easily

Moshi - JSON library for Android

Moshi Converter - A Converter that uses Moshi for serialization to and from JSON.

Coil-kt - An image loading library for Android backed by Kotlin Coroutines

Material Components for Android - Modular and customizable Material Design UI components for Android


# Architecture


This app uses Model View View-Mode ( MVVM ) architecture
