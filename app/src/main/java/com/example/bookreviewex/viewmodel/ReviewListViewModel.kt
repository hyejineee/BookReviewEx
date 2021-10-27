package com.example.bookreviewex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookreviewex.repository.model.ReviewEntity
import com.example.bookreviewex.usecase.GetReviewListUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ReviewListViewModel(
    private val getReviewListUseCase: GetReviewListUseCase
):ViewModel() {

    var reviewList = emptyList<ReviewEntity>()

    fun fetchAllReview(): Job = viewModelScope.launch {
        reviewList = getReviewListUseCase()
    }
}