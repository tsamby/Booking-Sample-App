package com.wizzpass.mybookrooms.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.booking_app.model.BookRoom
import kotlinx.coroutines.flow.Flow

/**
 * Created by novuyo on 10,November,2021
 */
@Dao
interface BookRoomsDao {
    /**
     * Inserts [books] into the [Books.TABLE_NAME] table.
     * Duplicate values are replaced in the table.
     * @param books Books
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookRoomss(books: List<BookRoom>)

    /**
     * Deletes all the books from the [Books.TABLE_NAME] table.
     */
    @Query("DELETE FROM ${BookRoom.TABLE_NAME}")
    suspend fun deleteAllBooks()

    /**
     * Fetches the book from the [Book.TABLE_NAME] table whose name is [name].
     * @param name Unique ID of [Book]
     * @return [Flow] of [Book] from database table.
     */
    @Query("SELECT * FROM ${BookRoom.TABLE_NAME} WHERE name = :name")
    fun getBookByName(name: String): Flow<BookRoom>

    /**
     * Fetches all the books from the [Book.TABLE_NAME] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${BookRoom.TABLE_NAME}")
    fun getAllBooks(): Flow<List<BookRoom>>
}