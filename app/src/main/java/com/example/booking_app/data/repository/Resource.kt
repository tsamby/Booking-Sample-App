package com.example.booking_app.data.repository

/**
 * Created by novuyo on 10,November,2021
 */
sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Failed<T>(val message: String) : Resource<T>()
}
