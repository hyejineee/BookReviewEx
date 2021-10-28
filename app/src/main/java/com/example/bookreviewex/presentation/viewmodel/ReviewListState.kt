package com.example.bookreviewex.presentation.viewmodel

import com.example.bookreviewex.repository.localdb.entity.ReviewEntity

sealed class ReviewListState {
    object Idle : ReviewListState()
    object Loading : ReviewListState()
    data class Success(val reviewList:List<ReviewEntity>):ReviewListState()
    object Error:ReviewListState()
}
