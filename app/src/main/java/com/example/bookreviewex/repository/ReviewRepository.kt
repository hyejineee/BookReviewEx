package com.example.bookreviewex.repository

import com.example.bookreviewex.repository.localdb.entity.ReviewEntity

interface ReviewRepository {
    suspend fun getAllReview():List<ReviewEntity>
    suspend fun insertReview(reviewEntity: ReviewEntity):Long
    suspend fun insertReviewList(reviewEntities: List<ReviewEntity>)
    suspend fun getReviewByBookIsbn(isbn:String):ReviewEntity?
}