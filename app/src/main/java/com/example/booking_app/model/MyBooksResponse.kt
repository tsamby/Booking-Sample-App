package com.example.booking_app.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by novuyo on 12,November,2021
 */
@JsonClass(generateAdapter = true)
data class MyBooksResponse (
    @Json(name = "rooms")
    val rooms: List<BookRoom>
)