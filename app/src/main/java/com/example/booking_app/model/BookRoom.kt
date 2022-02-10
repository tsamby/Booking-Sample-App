package com.example.booking_app.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.booking_app.model.BookRoom.CREATOR.TABLE_NAME
import com.squareup.moshi.JsonClass

/**
 * Created by novuyo on 10,November,2021
 */
/**
 * Data class for Database entity and Serialization.
 */
@JsonClass(generateAdapter = true)
@Entity(tableName = TABLE_NAME)

data class BookRoom (

    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "spots")
    val spots: Int,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

//    companion object {
//        const val TABLE_NAME = "book_rooms"
//    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(spots)
        parcel.writeString(thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookRoom> {
        override fun createFromParcel(parcel: Parcel): BookRoom {
            return BookRoom(parcel)
        }

        override fun newArray(size: Int): Array<BookRoom?> {
            return arrayOfNulls(size)
        }

        const val TABLE_NAME = "book_rooms"
    }
}
