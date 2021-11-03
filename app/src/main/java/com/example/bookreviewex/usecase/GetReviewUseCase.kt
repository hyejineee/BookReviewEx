package com.example.bookreviewex.usecase

import com.example.bookreviewex.repository.BookRepository
import com.example.bookreviewex.repository.ReviewRepository
import com.example.bookreviewex.repository.localdb.entity.ReviewEntity

class GetReviewUseCase(private val repository: ReviewRepository) {

    suspend operator fun invoke(bookIsbn:String): ReviewEntity? {
        return repository.getReviewByBookIsbn(bookIsbn)
    }

}
