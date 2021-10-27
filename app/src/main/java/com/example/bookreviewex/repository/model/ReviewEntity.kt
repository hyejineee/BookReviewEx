package com.example.bookreviewex.repository.model

data class ReviewEntity(
    val id:Int,
    val book : BookDTO,
    val reviewDate :String,
    val content : String,
)