package com.example.bookreviewex.repository

import com.example.bookreviewex.repository.localdb.entity.ReviewEntity

class TestReviewRepository:ReviewRepository {

    val reviewList = mutableListOf<ReviewEntity>()

    override suspend fun getAllReview(): List<ReviewEntity> {
        return reviewList
    }

    override suspend fun insertReview(reviewEntity: ReviewEntity): Long {
        this.reviewList.add(reviewEntity)
        return reviewEntity.id?.toLong()?:-1
    }

    override suspend fun insertReviewList(reviewEntities: List<ReviewEntity>) {
        this.reviewList.addAll(reviewEntities)
    }

    override suspend fun getReviewByBookIsbn(isbn: String): ReviewEntity? {
        return reviewList.find { it.book.isbn == isbn }
    }

    override suspend fun updateReview(updatedReview: ReviewEntity): ReviewEntity? {
        return reviewList.find { it.id == updatedReview.id }?.copy(content = updatedReview.content)
    }
}