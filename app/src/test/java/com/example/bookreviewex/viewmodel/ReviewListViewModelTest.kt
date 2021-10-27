package com.example.bookreviewex.viewmodel

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.bookreviewex.repository.model.BookDTO
import com.example.bookreviewex.repository.model.ReviewEntity
import com.example.bookreviewex.usecase.GetBooksFromAPIUseCase
import com.example.bookreviewex.usecase.GetReviewListUseCase
import com.example.bookreviewex.usecase.InsertReviewListUseCase
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.koin.test.inject

internal class ReviewListViewModelTest:ViewModelTest() {
    private val viewModel:ReviewListViewModel by inject()

    private val insertReviewListUseCase : InsertReviewListUseCase by inject()
    private val getReviewListUseCase: GetReviewListUseCase by inject()
    private val getBooksFromAPIUseCase: GetBooksFromAPIUseCase by inject()

    private val mockReviewedBooks = listOf(
        ReviewEntity(
            1,
            BookDTO(isbn = "1111",title = "title", image = "",description = "description" ),
            "2021.10.26",
            "content1"
        ),
        ReviewEntity(
            2,
            BookDTO(isbn = "2222", title = "title2", image = "",description = "description" ),
            "2021.10.26",
            "content2"
        )
    )

    /**
     *  1. 디비에서 리뷰가 달린 도서리스트 가져오기
     *  2. api서버에서 검색한 도서 리스트 가져오기
     * */

    @Before
    fun initData(){
        runBlockingTest {
            insertReviewListUseCase(mockReviewedBooks)
        }
    }

    @Test
    fun `get reviews from db `(){
        runBlockingTest {
            viewModel.fetchAllReview()
            assertThat(viewModel.reviewList).isEqualTo(mockReviewedBooks)
        }
    }

}