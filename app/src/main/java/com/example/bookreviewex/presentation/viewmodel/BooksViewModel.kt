package com.example.bookreviewex.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookreviewex.presentation.state.BookListState
import com.example.bookreviewex.usecase.GetBooksFromAPIUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class BooksViewModel(
    private val getBooksFromAPIUseCase: GetBooksFromAPIUseCase
): ViewModel() {

    private val _books = MutableLiveData<BookListState>(BookListState.Idle)
    val book:LiveData<BookListState> = _books

    fun searchBook(keyword:String) : Job = viewModelScope.launch {
        _books.value = BookListState.Loading
        try {
            _books.value = BookListState.Success(getBooksFromAPIUseCase(keyword))
        }catch (e:Exception){
            Log.d(TAG, "통신 에러 발생 : $e")
        }

    }

    companion object{
        private const val TAG = "BOOK_VIEW_MODEL"
    }

}