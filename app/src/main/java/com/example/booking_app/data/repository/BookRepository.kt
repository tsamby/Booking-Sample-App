package com.wizzpass.mybookrooms.data.repository

import androidx.annotation.MainThread
import com.example.booking_app.data.remote.api.MyBooksService
import com.example.booking_app.data.repository.NetworkBoundRepository
import com.example.booking_app.data.repository.Resource
import com.example.booking_app.model.BookRoom
import com.example.booking_app.model.MyBooksResponse
import com.wizzpass.mybookrooms.data.local.dao.BookRoomsDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by novuyo on 10,November,2021
 */
interface BookRepository {
    fun getAllBooks(): Flow<Resource<List<BookRoom>>>
    fun getBookByName(name: String): Flow<BookRoom>
}

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability. This is Single source of data.
 */
@ExperimentalCoroutinesApi
class DefaultBooksRepository @Inject constructor(
    private val bookroomsDao: BookRoomsDao,
    private val myBooksService: MyBooksService
) : BookRepository {

    /**
     * Fetched the posts from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    override fun getAllBooks(): Flow<Resource<List<BookRoom>>> {
        return object : NetworkBoundRepository<List<BookRoom>, MyBooksResponse>() {

           override suspend fun saveRemoteData(response:MyBooksResponse) = bookroomsDao.addBookRoomss(response.rooms)

           override fun fetchFromLocal(): Flow<List<BookRoom>> = bookroomsDao.getAllBooks()

            override suspend fun fetchFromRemote(): Response<MyBooksResponse> = myBooksService.getBooks()
        }.asFlow()
    }

    /**
     * Retrieves a book with specified [bookId].
     * @param bookId Unique id of a [BookRoom].
     * @return [BookRoom] data fetched from the database.
     */
    @MainThread
    override fun getBookByName(name: String): Flow<BookRoom> = bookroomsDao.getBookByName(name).distinctUntilChanged()
}