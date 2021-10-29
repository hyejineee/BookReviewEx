package com.example.bookreviewex.repository

import android.util.Log
import com.example.bookreviewex.repository.service.SearchApiService
import com.example.bookreviewex.repository.service.model.BookDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteBookRepository(
    private val apiService: SearchApiService,
    private val ioDispatcher: CoroutineDispatcher
) :BookRepository {
    override suspend fun getBooksByKeyword(keyword: String): List<BookDTO> = withContext(ioDispatcher){
        apiService.searchByKeyword(keyword).items
    }

    companion object{
        private const val TAG = "REMOTE_BOOK_REPOSITORY"
    }
}