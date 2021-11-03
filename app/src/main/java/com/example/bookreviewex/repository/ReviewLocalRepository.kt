package com.example.bookreviewex.repository

import com.example.bookreviewex.repository.localdb.dao.ReviewDAO
import com.example.bookreviewex.repository.localdb.entity.ReviewEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ReviewLocalRepository(
    private val reviewDAO : ReviewDAO,
    private val ioDispatcher : CoroutineDispatcher
):ReviewRepository {
    override suspend fun getAllReview(): List<ReviewEntity> = withContext(ioDispatcher){
        reviewDAO.getAllReview()
    }

    override suspend fun insertReview(reviewEntity: ReviewEntity): Long = withContext(ioDispatcher){
        reviewDAO.insertReview(reviewEntity)
    }

    override suspend fun insertReviewList(reviewEntities: List<ReviewEntity>) = withContext(ioDispatcher){
        reviewDAO.insertAllReview(reviewEntities)
    }

    override suspend fun getReviewByBookIsbn(isbn: String): ReviewEntity? {
        return reviewDAO.getReviewByBookIsbn(isbn)
    }
}