package com.example.bookreviewex.di

import android.content.Context
import androidx.room.Room
import com.example.bookreviewex.repository.ReviewLocalRepository
import com.example.bookreviewex.repository.ReviewRepository
import com.example.bookreviewex.repository.localdb.ReviewBookAppDatabase
import com.example.bookreviewex.usecase.GetReviewListUseCase
import com.example.bookreviewex.usecase.InsertReviewListUseCase
import com.example.bookreviewex.presentation.viewmodel.ReviewListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

//    localDatabaseModule

    //viewModel
    viewModel { ReviewListViewModel(get()) }

    //useCase
    factory { GetReviewListUseCase(get()) }
    factory { InsertReviewListUseCase(get()) }

    //repository
    single<ReviewRepository> { ReviewLocalRepository(get(), Dispatchers.IO) }
}

val localDatabaseModule = module {
    single { provideDB(androidApplication()) }
    single { provideReviewDao(get()) }
}

internal fun provideDB(context: Context):ReviewBookAppDatabase =
    Room.inMemoryDatabaseBuilder(context, ReviewBookAppDatabase::class.java).build()

internal fun provideReviewDao(database: ReviewBookAppDatabase) = database.reviewDAO()