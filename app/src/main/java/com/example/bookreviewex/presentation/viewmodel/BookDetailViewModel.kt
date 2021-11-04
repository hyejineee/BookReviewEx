package com.example.bookreviewex.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookreviewex.presentation.state.BookDetailState
import com.example.bookreviewex.repository.localdb.entity.ReviewEntity
import com.example.bookreviewex.usecase.GetReviewUseCase
import com.example.bookreviewex.usecase.InsertReviewUseCase
import com.example.bookreviewex.usecase.UpdateReviewUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class BookDetailViewModel(
    private val insertReviewUseCase: InsertReviewUseCase,
    private val getReviewUseCase: GetReviewUseCase,
    private val updateReviewUseCase: UpdateReviewUseCase,
) : ViewModel() {

    private val _review = MutableLiveData<BookDetailState>(BookDetailState.Idle)
    val review: LiveData<BookDetailState> = _review

    fun insertReview(reviewEntity: ReviewEntity): Job = viewModelScope.launch {
        _review.value = BookDetailState.Loading
        try {
            insertReviewUseCase(reviewEntity)
        } catch (e: Exception) {
            _review.value = BookDetailState.Error
        }

        _review.value = BookDetailState.InsertSuccess
    }

    fun getReviewByBookIsbn(bookIsbn: String): Job = viewModelScope.launch {
        _review.value = BookDetailState.Loading

        var data: ReviewEntity? = null

        try {
            data = getReviewUseCase(bookIsbn)
        } catch (e: Exception) {
            e.printStackTrace()
            _review.value = BookDetailState.Error
        }

        data ?: kotlin.run {
            _review.value = BookDetailState.Error
            return@launch
        }

        _review.value = BookDetailState.GetSuccess(data)
    }

    fun updateReview(reviewEntity: ReviewEntity): Job = viewModelScope.launch {

        _review.value = BookDetailState.Loading
        var updatedReview: ReviewEntity? = null

        try {
            updatedReview = updateReviewUseCase(reviewEntity)
        } catch (e: Exception) {
            e.printStackTrace()
            _review.value = BookDetailState.Error
        }

        updatedReview ?: kotlin.run {
            _review.value = BookDetailState.Error
            return@launch
        }

        _review.value = BookDetailState.UpdateSuccess(updatedReview)

    }
}