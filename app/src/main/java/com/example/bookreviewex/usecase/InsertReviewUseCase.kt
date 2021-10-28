package com.example.bookreviewex.usecase

import com.example.bookreviewex.repository.ReviewRepository
import com.example.bookreviewex.repository.localdb.entity.ReviewEntity

class InsertReviewUseCase(private val repository: ReviewRepository) {
    suspend operator fun invoke(reviewEntity: ReviewEntity):Long = repository.insertReview(reviewEntity)
}
