package com.example.bookapp.presentation.features.bookdetails.eventlisteners

import com.example.bookapp.data.local.BookEntity

interface BookDetailsEventListeners {
    fun onDeleteBook(book: BookEntity)
}