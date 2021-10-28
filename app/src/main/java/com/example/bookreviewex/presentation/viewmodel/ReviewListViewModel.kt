package com.example.bookreviewex.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookreviewex.usecase.GetReviewListUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ReviewListViewModel(
    private val getReviewListUseCase: GetReviewListUseCase
):ViewModel() {

    private val _reviewList = MutableLiveData<ReviewListState>(ReviewListState.Idle)
    val reviewList:LiveData<ReviewListState> = _reviewList

    fun fetchAllReview(): Job = viewModelScope.launch {
        _reviewList.value = ReviewListState.Loading
        _reviewList.value = ReviewListState.Success(getReviewListUseCase())
    }

}