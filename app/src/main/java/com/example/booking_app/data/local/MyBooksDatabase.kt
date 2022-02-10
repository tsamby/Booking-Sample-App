package com.example.booking_app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.booking_app.model.BookRoom
import com.wizzpass.mybookrooms.data.local.dao.BookRoomsDao


/**
 * Created by novuyo on 10,November,2021
 */
/**
 * Abstract MyBooks database.
 * It provides DAO [BooksDao] by using method [getBooksDao].
 */
@Database(
    entities = [BookRoom::class],
    version = DatabaseMigrations.DB_VERSION
)
abstract class MyBooksDatabase : RoomDatabase() {

    /**
     * @return [BooksDao] Books Data Access Object.
     */
    abstract fun getBooksDao(): BookRoomsDao

    companion object {
        private const val DB_NAME = "my_books_database"

        @Volatile
        private var INSTANCE: MyBooksDatabase? = null

        fun getInstance(context: Context): MyBooksDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyBooksDatabase::class.java,
                    DB_NAME
                ).addMigrations(*DatabaseMigrations.MIGRATIONS).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}