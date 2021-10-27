package com.example.bookreviewex.repository

import com.example.bookreviewex.repository.model.ReviewEntity

interface ReviewRepository {
    suspend fun getAllReview():List<ReviewEntity>
    suspend fun insertReview(reviewEntity: ReviewEntity):Long
    suspend fun insertReviewList(reviewEntities: List<ReviewEntity>)
}