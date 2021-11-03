package com.example.bookreviewex.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bookreviewex.databinding.ActivityBookDetailBinding
import com.example.bookreviewex.repository.service.model.BookDTO

class BookDetailActivity:AppCompatActivity() {

    lateinit var binding: ActivityBookDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<BookDTO>("data")
        data?.let { initView(it) }

    }

    private fun initView(data:BookDTO){
        binding.titleTextView.text = removeHtml(data.title)
        binding.descriptionTextView.text = removeHtml(data.description)
        Glide.with(this)
            .load(data.image)
            .centerCrop()
            .into(binding.bookCoverImageView)
    }

    companion object{
        private const val TAG ="BookDetailActivity"
    }

}
