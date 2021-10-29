package com.example.bookreviewex.repository.service.model

import com.google.gson.annotations.SerializedName

data class BookDTO (
    @SerializedName("isbn") val isbn:String,
    @SerializedName("title") val title:String,
    @SerializedName("image") val image:String,
    @SerializedName("description") val description :String
)
