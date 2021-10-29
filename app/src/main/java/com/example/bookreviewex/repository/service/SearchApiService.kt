package com.example.bookreviewex.repository.service

import com.example.bookreviewex.repository.service.model.SearchResponseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {
    @GET("book.json")
     suspend fun searchByKeyword(
        @Query("query")keyword:String,
        @Query("display") count:Int = 10,
        @Query("start")start:Int =1
    ): SearchResponseDTO

}