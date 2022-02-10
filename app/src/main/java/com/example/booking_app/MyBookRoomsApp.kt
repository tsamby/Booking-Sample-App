package com.example.booking_app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.booking_app.utils.isNight
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by novuyo on 10,November,2021
 */
@HiltAndroidApp
class MyBookRoomsApp : Application(){

    override fun onCreate() {
        super.onCreate()

        // Get UI mode and set
        val mode = if (isNight()) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        AppCompatDelegate.setDefaultNightMode(mode)
    }
}

