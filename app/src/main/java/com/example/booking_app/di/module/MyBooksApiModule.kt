package com.example.booking_app.di.module


import com.example.booking_app.data.remote.api.MyBooksService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by novuyo on 10,November,2021
 */

@InstallIn(SingletonComponent::class)
@Module
class MyBooksApiModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): MyBooksService = Retrofit.Builder()
        .baseUrl(MyBooksService.MYBOOKS_API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create()
        )
        .build()
        .create(MyBooksService::class.java)
}
