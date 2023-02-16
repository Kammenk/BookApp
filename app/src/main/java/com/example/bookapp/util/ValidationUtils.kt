package com.example.bookapp.util

import android.webkit.URLUtil

object ValidationUtils {

    fun isURLProvided(url: String) = URLUtil.isValidUrl(url)
    fun isTitleOrDescriptionValid(text: String) = text.isEmpty()
}