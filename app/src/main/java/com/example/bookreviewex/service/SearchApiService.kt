package com.example.bookreviewex.service

import com.example.bookreviewex.model.SearchResponseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {
    @GET("")
    fun searchByKeyword(
        @Query("query")keyword:String,
        @Query("display") count:Int = 10,
        @Query("start")start:Int =1
    ): Call<SearchResponseDTO>

}