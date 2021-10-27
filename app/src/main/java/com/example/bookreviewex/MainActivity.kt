package com.example.bookreviewex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookreviewex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private val handleSearchButton = {
        val keyword = binding.searchEditTextView.text
        //todo 도서 검색 api로 요청하기
    }
}