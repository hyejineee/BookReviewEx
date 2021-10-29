package com.example.bookreviewex.repository

import com.example.bookreviewex.repository.service.model.BookDTO

interface BookRepository {
    suspend fun getBooksByKeyword(keyword:String) :List<BookDTO>
}
