package com.example.bookapp.domain.repository

import com.example.bookapp.data.local.BookEntity
import com.example.bookapp.util.Resource

interface IBooksRepository {
    suspend fun getAllBooks(): Resource<List<BookEntity>>
    suspend fun addBook(book: BookEntity): Long
    suspend fun deleteBook(bookEntity: BookEntity)
    suspend fun getBooksByName(bookTitle: String): Resource<List<BookEntity>>
}