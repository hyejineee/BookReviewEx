package com.example.bookreviewex.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.bookreviewex.R
import com.example.bookreviewex.presentation.viewmodel.BookDetailViewModel
import com.example.bookreviewex.presentation.viewmodel.BooksViewModel
import com.example.bookreviewex.repository.ReviewLocalRepository
import com.example.bookreviewex.repository.ReviewRepository
import com.example.bookreviewex.repository.localdb.ReviewBookAppDatabase
import com.example.bookreviewex.presentation.viewmodel.ReviewListViewModel
import com.example.bookreviewex.repository.BookRepository
import com.example.bookreviewex.repository.RemoteBookRepository
import com.example.bookreviewex.repository.service.SearchApiService
import com.example.bookreviewex.usecase.*
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModules = module {

//    localDatabaseModule

    //viewModel
    viewModel { ReviewListViewModel(get()) }
    viewModel{ BooksViewModel(get())}
    viewModel{BookDetailViewModel(get(), get(), get())}

    //useCase
    factory { GetReviewListUseCase(get()) }
    factory { InsertReviewListUseCase(get()) }
    factory { GetBooksFromAPIUseCase(get()) }
    factory { InsertReviewUseCase(get()) }
    factory { GetReviewUseCase(get()) }
    factory { UpdateReviewUseCase(get()) }

    //repository
    single<ReviewRepository> { ReviewLocalRepository(get(), Dispatchers.IO) }
    single<BookRepository>{ RemoteBookRepository(get(), Dispatchers.IO) }
}

val networkModules = module {
    single { provideHTTPClient(androidApplication()) }
    single { provideApiService(get()) }

}

val localDatabaseModule = module {
    single { provideDB(androidApplication()) }
    single { provideReviewDao(get()) }
}

internal fun provideHTTPClient(context: Context):Retrofit {
    val id = context.resources.getString(R.string.naverSearchApiId)
    val secret = context.resources.getString(R.string.naverSearchApiSecret)

    val httpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", id)
                .addHeader("X-Naver-Client-Secret", secret)
                .build()
            it.proceed(request)
        }
        .build()

    return Retrofit.Builder()
        .client(httpClient)
        .baseUrl("https://openapi.naver.com/v1/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

internal fun provideApiService(retrofit:Retrofit) = retrofit.create(SearchApiService::class.java)

internal fun provideDB(context: Context):ReviewBookAppDatabase =
    Room.inMemoryDatabaseBuilder(context, ReviewBookAppDatabase::class.java).build()

internal fun provideReviewDao(database: ReviewBookAppDatabase) = database.reviewDAO()