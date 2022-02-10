package com.example.booking_app.ui.roomdetail

import androidx.lifecycle.ViewModel
import com.wizzpass.mybookrooms.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class RoomDetailViewModel @Inject constructor(
    bookRepository: BookRepository,
    ) :
    ViewModel()  {

    //val room = bookRepository.getBookByName(postId).asLiveData()
}