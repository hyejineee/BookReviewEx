package com.example.bookreviewex.usecase

import com.example.bookreviewex.repository.ReviewRepository
import com.example.bookreviewex.repository.localdb.entity.ReviewEntity

class UpdateReviewUseCase(private val repository:ReviewRepository){
    suspend operator fun invoke(updatedReview:ReviewEntity): ReviewEntity? {
        return repository.updateReview(updatedReview)
    }
}
