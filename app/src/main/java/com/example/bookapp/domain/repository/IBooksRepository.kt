package com.example.bookapp.domain.repository

import com.example.bookapp.data.local.BookEntity
import com.example.bookapp.util.Resource

interface IBooksRepository {

    suspend fun getBookDetails(bookId: Int): Resource<BookEntity>
    suspend fun getAllBooks(): Resource<List<BookEntity>>
    suspend fun addBook(book: BookEntity)
    suspend fun deleteBook(bookEntity: BookEntity)
}