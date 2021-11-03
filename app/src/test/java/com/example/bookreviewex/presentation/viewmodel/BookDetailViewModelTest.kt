package com.example.bookreviewex.presentation.viewmodel

import com.example.bookreviewex.presentation.state.BookDetailState
import com.example.bookreviewex.repository.localdb.entity.ReviewEntity
import com.example.bookreviewex.repository.service.model.BookDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import java.text.SimpleDateFormat

@ExperimentalCoroutinesApi
internal class BookDetailViewModelTest:ViewModelTest() {
    private val bookDetailViewModel:BookDetailViewModel by inject()

    // 책의 리뷰 데이터 저장하기

    val mockBook = BookDTO(
        isbn = "1111",
        title = "test title",
        description = "this is test book",
        image = ""
    )

    val currentTime = System.currentTimeMillis()
    val date = SimpleDateFormat("yyyy.MM.dd").format(currentTime)

    val mockReview = ReviewEntity(
        id = 1,
        book = mockBook,
        reviewDate = date,
        content = "this is test review"
    )

    @Test
    fun `책의 리뷰 데이터 저장하기`(){
        runBlocking {
            val testObserver = bookDetailViewModel.review.test()

            bookDetailViewModel.insertReview(mockReview)

            testObserver.assertValueSequence(
                listOf(
                    BookDetailState.Idle,
                    BookDetailState.Loading,
                    BookDetailState.InsertSuccess
                )
            )
        }
    }

    @Test
    fun `리뷰 데이터 가져오기`(){
        runBlocking {
            val testObserver = bookDetailViewModel.review.test()

            bookDetailViewModel.insertReview(mockReview)

            bookDetailViewModel.getReviewByBookIsbn("1111")

            testObserver.assertValueSequence(
                listOf(
                    BookDetailState.Idle,
                    BookDetailState.Loading,
                    BookDetailState.InsertSuccess,
                    BookDetailState.Loading,
                    BookDetailState.GetSuccess(mockReview)
                )
            )
        }

    }





}