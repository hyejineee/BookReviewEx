package com.example.bookreviewex.usecase

import com.example.bookreviewex.repository.BookRepository
import com.example.bookreviewex.repository.service.model.BookDTO

class GetBooksFromAPIUseCase(private val repository: BookRepository) {
    suspend operator fun invoke(keyword:String):List<BookDTO> = repository.getBooksByKeyword(keyword)
}