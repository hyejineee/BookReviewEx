package com.example.bookreviewex.presentation

import android.text.Html

fun removeHtml(str:String): String {
    return Html.fromHtml(str).toString()
}