package com.example.bookreviewex.repository

import com.example.bookreviewex.repository.service.model.BookDTO

class TestRemoteBooksRepository:BookRepository{
    var items = mutableListOf<BookDTO>()

    override suspend fun getBooksByKeyword(keyword: String): List<BookDTO> {
        return items.filter { it.title.contains(keyword) }
    }

}
