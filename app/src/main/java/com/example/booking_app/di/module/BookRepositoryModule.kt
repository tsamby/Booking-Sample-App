package com.example.booking_app.di.module

import com.wizzpass.mybookrooms.data.repository.BookRepository
import com.wizzpass.mybookrooms.data.repository.DefaultBooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by novuyo on 10,November,2021
 */

/**
 * Currently BookRepository is only used in ViewModels.
 * BookDetailsViewModel is not injected using @HiltViewModel so can't install in ViewModelComponent.
 */
@ExperimentalCoroutinesApi
@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class BookRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindBookRepository(repository: DefaultBooksRepository): BookRepository
}