package com.example.bookreviewex.presentation.viewmodel

import com.example.bookreviewex.repository.service.model.BookDTO

sealed class BookListState {
    object Idle:BookListState()
    object Loading:BookListState()
    data class Success(val books:List<BookDTO>):BookListState()

}
