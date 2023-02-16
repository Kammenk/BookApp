package com.example.bookapp.presentation.features.booklist.eventlisteners

import com.example.bookapp.data.local.BookEntity

interface BookListEventListeners {
    fun onItemClicked(book: BookEntity)
    fun onItemAdd()
    fun searchBookByTitle(title: String)
}