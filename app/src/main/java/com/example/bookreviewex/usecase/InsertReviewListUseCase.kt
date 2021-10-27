package com.example.bookreviewex.usecase

import com.example.bookreviewex.repository.ReviewRepository
import com.example.bookreviewex.repository.model.ReviewEntity

class InsertReviewListUseCase(private val repository: ReviewRepository) {
    suspend operator fun invoke(reviewEntities: List<ReviewEntity>) =
        repository.insertReviewList(reviewEntities)
}
