package com.example.booking_app.di.module

import android.app.Application
import com.example.booking_app.data.local.MyBooksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by novuyo on 10,November,2021
 */


@InstallIn(SingletonComponent::class)
@Module
class MyBooksDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = MyBooksDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePostsDao(database: MyBooksDatabase) = database.getBooksDao()
}
