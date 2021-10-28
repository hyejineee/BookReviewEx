package com.example.bookreviewex.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookreviewex.databinding.ActivityMainBinding
import com.example.bookreviewex.repository.localdb.entity.ReviewEntity
import com.example.bookreviewex.repository.service.model.BookDTO
import com.example.bookreviewex.usecase.InsertReviewListUseCase
import com.example.bookreviewex.presentation.viewmodel.ReviewListViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    private val reviewListViewModel:ReviewListViewModel by inject()

    private val insetReviewListUseCase : InsertReviewListUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mockReviewedBooks = listOf(
            ReviewEntity(
                1,
                BookDTO(isbn = "1111",title = "title", image = "",description = "description" ),
                "2021.10.26",
                "content1"
            ),
            ReviewEntity(
                2,
                BookDTO(isbn = "2222", title = "title2", image = "",description = "description" ),
                "2021.10.26",
                "content2"
            )
        )

        GlobalScope.launch {
            insetReviewListUseCase(mockReviewedBooks)
        }

        reviewListViewModel.fetchAllReview()
    }

    private val handleSearchButton = {
        val keyword = binding.searchEditTextView.text
        //todo 도서 검색 api로 요청하기
    }
}