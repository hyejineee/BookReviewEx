package com.example.bookreviewex.repository

import com.example.bookreviewex.repository.model.ReviewEntity

class TestReviewRepository:ReviewRepository {

    val reviewList = mutableListOf<ReviewEntity>()

    override suspend fun getAllReview(): List<ReviewEntity> {
        return reviewList
    }

    override suspend fun insertReview(reviewEntity: ReviewEntity): Long {
        this.reviewList.add(reviewEntity)
        return reviewEntity.id.toLong()
    }

    override suspend fun insertReviewList(reviewEntities: List<ReviewEntity>) {
        this.reviewList.addAll(reviewEntities)
    }
}