package com.example.bookreviewex.repository.localdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bookreviewex.repository.localdb.entity.ReviewEntity

@Dao
interface ReviewDAO {
    @Query("select * from ReviewEntity")
    suspend fun getAllReview(): List<ReviewEntity>

    @Insert
    suspend fun insertReview(review:ReviewEntity):Long

    @Insert
    suspend fun insertAllReview(reviews : List<ReviewEntity>)

    @Query("select * from ReviewEntity where book_isbn = :isbn")
    suspend fun getReviewByBookIsbn(isbn: String): ReviewEntity?

    @Update
    suspend fun updateReview(updatedReview: ReviewEntity):Int
}
