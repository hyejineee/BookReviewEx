package com.example.bookreviewex.presentation.viewmodel

import com.example.bookreviewex.presentation.state.ReviewListState
import com.example.bookreviewex.repository.service.model.BookDTO
import com.example.bookreviewex.repository.localdb.entity.ReviewEntity
import com.example.bookreviewex.usecase.InsertReviewListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.koin.test.inject

@ExperimentalCoroutinesApi
internal class ReviewListViewModelTest:ViewModelTest() {


    private val viewModel:ReviewListViewModel by inject()

    private val insertReviewListUseCase : InsertReviewListUseCase by inject()

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


    @Before
    fun initData(){
        runBlockingTest {
            insertReviewListUseCase(mockReviewedBooks)
        }
    }

    @Test
    fun `get reviews from db `(){
        runBlockingTest {
            val testObserver = viewModel.reviewList.test()

            viewModel.fetchAllReview()

            testObserver.assertValueSequence(
                listOf(
                    ReviewListState.Idle,
                    ReviewListState.Loading,
                    ReviewListState.Success(mockReviewedBooks)
                )
            )
        }
    }

}