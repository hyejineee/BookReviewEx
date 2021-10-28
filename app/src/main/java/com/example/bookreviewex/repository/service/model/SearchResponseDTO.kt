package com.example.bookreviewex.repository.service.model

import com.google.gson.annotations.SerializedName

data class SearchResponseDTO(
    @SerializedName("total") val total: Int,
    @SerializedName("start") val start: Int,
    @SerializedName("display") val display: Int,
    @SerializedName("items") val items: List<BookDTO>
) {

}
