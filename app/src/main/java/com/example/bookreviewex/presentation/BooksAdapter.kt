package com.example.bookreviewex.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.bookreviewex.databinding.ItemReviewBinding
import com.example.bookreviewex.repository.service.model.BookDTO

class  BooksAdapter():ListAdapter<BookDTO, BooksAdapter.ViewHolder>(BooksCallback){

    inner class ViewHolder(private val binding: ItemReviewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data:BookDTO){
            binding.titleTextView.text = data.title
            binding.contentTextView.text = data.description
        }
    }

    companion object{
        object BooksCallback : DiffUtil.ItemCallback<BookDTO>(){
            override fun areItemsTheSame(oldItem: BookDTO, newItem: BookDTO): Boolean {
                return oldItem.isbn == newItem.isbn
            }

            override fun areContentsTheSame(oldItem: BookDTO, newItem: BookDTO): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}