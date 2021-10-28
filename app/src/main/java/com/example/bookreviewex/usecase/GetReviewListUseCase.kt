package com.example.bookreviewex.usecase

import com.example.bookreviewex.repository.ReviewRepository
import com.example.bookreviewex.repository.localdb.entity.ReviewEntity

class GetReviewListUseCase(private val repository: ReviewRepository) {
    suspend operator fun invoke() : List<ReviewEntity> = repository.getAllReview()
}