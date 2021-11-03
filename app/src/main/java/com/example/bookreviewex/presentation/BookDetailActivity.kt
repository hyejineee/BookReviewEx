package com.example.bookreviewex.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bookreviewex.databinding.ActivityBookDetailBinding
import com.example.bookreviewex.presentation.state.BookDetailState
import com.example.bookreviewex.presentation.viewmodel.BookDetailViewModel
import com.example.bookreviewex.repository.localdb.entity.ReviewEntity
import com.example.bookreviewex.repository.service.model.BookDTO
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat

class BookDetailActivity:AppCompatActivity() {

    lateinit var binding: ActivityBookDetailBinding
    private val bookDetailViewModel : BookDetailViewModel by inject()
    private var reviewEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reviewData = intent.getParcelableExtra<ReviewEntity>("review")
        val data = intent.getParcelableExtra<BookDTO>("data")

        if(data!=null && reviewData!=null){
            reviewEditMode = true
            initView(data, reviewData)
        }else{
            data?: kotlin.run { finish() }
            data?.let {
                initView(data, null)
                bookDetailViewModel.getReviewByBookIsbn(data.isbn)
            }
        }

        subscribeObserver()
    }

    private fun initView(data:BookDTO, reviewEntity: ReviewEntity?){
        binding.addReviewButton.text = if(reviewEditMode) "리뷰 업데이트" else "리뷰 등록"
        binding.titleTextView.text = removeHtml(data.title)
        binding.descriptionTextView.text = removeHtml(data.description)
        Glide.with(this)
            .load(data.image)
            .into(binding.bookCoverImageView)

        binding.addReviewButton.setOnClickListener {

            val date = SimpleDateFormat("yyyy.MM.dd").format(System.currentTimeMillis())

            val review = ReviewEntity(
                id = null,
                book = data,
                content = binding.reviewEditTextView.text.toString(),
                reviewDate = date
            )

            if(reviewEditMode){
                bookDetailViewModel.updateReview(review)
                return@setOnClickListener
            }

            bookDetailViewModel.insertReview(review)
        }

        reviewEntity?.let {
            binding.reviewEditTextView.setText(reviewEntity.content)
        }
    }

    private fun subscribeObserver(){
        bookDetailViewModel.review.observe(this){
            when(it){
                BookDetailState.Error -> handleError()
                is BookDetailState.GetSuccess -> handleGetSuccess(it)
                BookDetailState.Idle -> handleIdle()
                is BookDetailState.InsertSuccess -> handleInsertSuccess(it)
                BookDetailState.Loading -> handleLoading()
            }
        }
    }

    private fun handleGetSuccess(state:BookDetailState.GetSuccess){
        binding.reviewEditTextView.setText(state.review.content)
    }

    private fun handleInsertSuccess(state: BookDetailState.InsertSuccess){
        Snackbar.make(binding.root,"리뷰 등록 성공", Snackbar.LENGTH_SHORT ).show()
    }

    private fun handleLoading(){

    }

    private fun handleError(){

    }

    private fun handleIdle(){

    }


    companion object{
        private const val TAG ="BookDetailActivity"
    }

}
