package com.example.bookreviewex.presentation.viewmodel

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.bookreviewex.repository.BookRepository
import com.example.bookreviewex.repository.TestRemoteBooksRepository
import com.example.bookreviewex.repository.service.model.BookDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject

@ExperimentalCoroutinesApi
internal class BooksViewModelTest:ViewModelTest() {

    // 1. 서버로부터 도서 데이터 가져오기
    private val booksViewModel : BooksViewModel by inject()
    private val testRepository: BookRepository by inject()

    val mockBooks = List(10){
        BookDTO(
            isbn = "$it",
            title = "title $it",
            image = "",
            description = "description $it"
        )
    }
    val mockBooks2 = List(10){
        BookDTO(
            isbn = "$it",
            title = "another $it",
            image = "",
            description = "description $it"
        )
    }


    @Before
    fun dataInit(){
        (testRepository as TestRemoteBooksRepository).items = mockBooks.plus(mockBooks2).toMutableList()
    }


    @Test
    fun `서버로부터 도서 데이터 가져오기`(){
        runBlocking {
            val testObserver = booksViewModel.book.test()

            booksViewModel.searchBook("title")

            testObserver.assertValueSequence(
                listOf(
                    BookListState.Idle,
                    BookListState.Loading,
                    BookListState.Success(mockBooks)
                )
            )
        }
    }


}