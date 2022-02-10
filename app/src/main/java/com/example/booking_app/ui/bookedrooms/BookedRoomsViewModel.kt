package com.example.booking_app.ui.bookedrooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookedRoomsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "My Bookings"
    }
    val text: LiveData<String> = _text
}