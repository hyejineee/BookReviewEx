package com.example.bookreviewex.di

import com.example.bookreviewex.viewmodel.ReviewListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel { ReviewListViewModel(get()) }
}