package com.example.booking_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.booking_app.databinding.ItemBookRoomBinding
import com.example.booking_app.model.BookRoom
import com.example.booking_app.ui.viewholder.BookingRoomViewHolder


/**
 * Created by novuyo on 12,November,2021
 */
class BookingRoomAdapter(
    private val onItemClicked: (BookRoom, ImageView) -> Unit
) : ListAdapter<BookRoom, BookingRoomViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BookingRoomViewHolder(
        ItemBookRoomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: BookingRoomViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BookRoom>() {
            override fun areItemsTheSame(oldItem: BookRoom, newItem: BookRoom): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: BookRoom, newItem: BookRoom): Boolean =
                oldItem == newItem
        }
    }
}