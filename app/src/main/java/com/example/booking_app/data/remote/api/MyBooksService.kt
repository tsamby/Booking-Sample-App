package com.example.booking_app.data.remote.api

import com.example.booking_app.model.MyBooksResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by novuyo on 10,November,2021
 */

interface MyBooksService {

    @GET("all")
    suspend fun getBooks(): Response<MyBooksResponse>

    companion object {
        const val MYBOOKS_API_URL = "https://novuyo.deeplytix.net/novuyo/"
    }
}
