package com.example.bookreviewex.presentation.state

import com.example.bookreviewex.repository.localdb.entity.ReviewEntity

sealed class BookDetailState {
    object Idle:BookDetailState()
    object Error:BookDetailState()
    object Loading:BookDetailState()
    data class GetSuccess(val review:ReviewEntity):BookDetailState()
    object InsertSuccess:BookDetailState()

}
