package com.example.bookreviewex.di

import com.example.bookreviewex.repository.ReviewRepository
import com.example.bookreviewex.repository.TestReviewRepository
import com.example.bookreviewex.usecase.GetReviewListUseCase
import com.example.bookreviewex.usecase.InsertReviewListUseCase
import com.example.bookreviewex.viewmodel.ReviewListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testModules = module {

    //viewmodel
    viewModel { ReviewListViewModel(get()) }

    //repository
    single<ReviewRepository> { TestReviewRepository() }

    //usecase
    factory { InsertReviewListUseCase(get()) }
    factory { GetReviewListUseCase(get()) }

}