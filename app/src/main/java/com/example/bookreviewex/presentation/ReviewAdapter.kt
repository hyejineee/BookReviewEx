package com.example.bookreviewex.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreviewex.databinding.ItemReviewBinding
import com.example.bookreviewex.repository.localdb.entity.ReviewEntity

class ReviewAdapter(
) : ListAdapter<ReviewEntity, ReviewAdapter.ViewHolder>(DiffCallback) {

    inner class ViewHolder(private val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reviewItem:ReviewEntity) {
            binding.contentTextView.text = reviewItem.content
            binding.titleTextView.text = reviewItem.book.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemReviewBinding.inflate(LayoutInflater.from(parent.context) ,parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object {
        object DiffCallback : DiffUtil.ItemCallback<ReviewEntity>() {
            override fun areItemsTheSame(oldItem: ReviewEntity, newItem: ReviewEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ReviewEntity, newItem: ReviewEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

}