package com.example.bookreviewex.repository.service.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookDTO (
    @SerializedName("isbn") val isbn:String,
    @SerializedName("title") val title:String,
    @SerializedName("image") val image:String,
    @SerializedName("description") val description :String
) : Parcelable
