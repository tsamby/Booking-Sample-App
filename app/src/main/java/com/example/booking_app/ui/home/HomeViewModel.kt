package com.example.booking_app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking_app.model.BookRoom
import com.example.booking_app.model.State
import com.wizzpass.mybookrooms.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for [HomeFragment]
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(private val bookRepository: BookRepository) :
    ViewModel() {

    private val _booksLiveData = MutableLiveData<State<List<BookRoom>>>()

    val booksLiveData: LiveData<State<List<BookRoom>>> = _booksLiveData


    fun getBooks() {
        viewModelScope.launch {
            bookRepository.getAllBooks()
                .onStart { _booksLiveData.value = State.loading() }
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _booksLiveData.value = state  }


        }
    }
}
