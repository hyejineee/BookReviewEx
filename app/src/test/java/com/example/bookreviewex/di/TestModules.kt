package com.example.bookreviewex.di

import com.example.bookreviewex.presentation.viewmodel.BooksViewModel
import com.example.bookreviewex.repository.ReviewRepository
import com.example.bookreviewex.repository.TestReviewRepository
import com.example.bookreviewex.usecase.GetReviewListUseCase
import com.example.bookreviewex.usecase.InsertReviewListUseCase
import com.example.bookreviewex.presentation.viewmodel.ReviewListViewModel
import com.example.bookreviewex.repository.BookRepository
import com.example.bookreviewex.repository.TestRemoteBooksRepository
import com.example.bookreviewex.usecase.GetBooksFromAPIUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testModules = module {

    //viewmodel
    viewModel { ReviewListViewModel(get()) }
    viewModel{BooksViewModel(get())}

    //repository
    single<ReviewRepository> { TestReviewRepository() }
    single<BookRepository> { TestRemoteBooksRepository() }

    //usecase
    factory { InsertReviewListUseCase(get()) }
    factory { GetReviewListUseCase(get()) }
    factory { GetBooksFromAPIUseCase(get()) }

}