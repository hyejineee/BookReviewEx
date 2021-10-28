package com.example.bookreviewex.repository.localdb.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookreviewex.repository.service.model.BookDTO

@Entity
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @Embedded(prefix = "book_") val book : BookDTO,
    val reviewDate :String,
    val content : String,
)