package com.example.booking_app.data.local

/**
 * Created by novuyo on 10,November,2021
 */
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.booking_app.model.BookRoom


object DatabaseMigrations {
    const val DB_VERSION = 1

    val MIGRATIONS: Array<Migration>
        get() = arrayOf<Migration>(
            migration12()
        )

    private fun migration12(): Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${BookRoom.TABLE_NAME} ADD COLUMN body TEXT")
        }
    }
}
