package com.example.bookapp.presentation.features.addBook.eventlisteners

import com.example.bookapp.data.local.BookEntity

interface AddBookEventListener {
    fun onBookAdd(book: BookEntity)
}