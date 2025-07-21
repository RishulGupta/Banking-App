package com.uilover.project2322.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uilover.project2322.Domain.Friend
import com.uilover.project2322.databinding.ViewholderFriendsBinding

class FriendsAdapter(private val list: ArrayList<Friend>) :
    RecyclerView.Adapter<FriendsAdapter.Viewholder>() {
    class Viewholder(val binding: ViewholderFriendsBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = ViewholderFriendsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(list[position].imageUrl)
            .into(holder.binding.img)
    }

    override fun getItemCount(): Int = list.size
}