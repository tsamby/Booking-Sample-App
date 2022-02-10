package com.example.booking_app.ui.viewholder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.booking_app.R
import com.example.booking_app.databinding.ItemBookRoomBinding
import com.example.booking_app.model.BookRoom

/**
 * Created by novuyo on 12,November,2021
 */
/**
 * [RecyclerView.ViewHolder] implementation to inflate View for RecyclerView.
 * See [BookingRoomAdapter]]
 */
class BookingRoomViewHolder(private val binding: ItemBookRoomBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(room: BookRoom, onItemClicked: (BookRoom, ImageView) -> Unit) {
        binding.roomName.text = room.name
        binding.roomSpots.text = room.spots.toString()
        binding.imageView.load(room.thumbnail) {
            placeholder(R.drawable.ic_photo)
            error(R.drawable.ic_broken_image)
        }

        binding.root.setOnClickListener {
            onItemClicked(room, binding.imageView)
        }
    }
}