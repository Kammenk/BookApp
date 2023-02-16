package com.example.bookapp.data.repository

import com.example.bookapp.data.local.BookEntity
import com.example.bookapp.data.local.BooksDao
import com.example.bookapp.domain.repository.IBooksRepository
import com.example.bookapp.util.Resource
import javax.inject.Inject

class BooksRepository @Inject constructor(private val booksDao: BooksDao) : IBooksRepository {

    override suspend fun getAllBooks(): Resource<List<BookEntity>> =
        try {
            Resource.Success(
                booksDao.getAllBooks()
            )
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
            Resource.Error(ex.message ?: "An error occurred.")
        }

    override suspend fun addBook(book: BookEntity): Long = booksDao.addBook(book)

    override suspend fun deleteBook(bookEntity: BookEntity) {
        booksDao.deleteBook(bookEntity)
    }

    override suspend fun getBooksByName(bookTitle: String): Resource<List<BookEntity>> = try {
        Resource.Success(
            booksDao.getBooksByName(bookTitle)
        )
    } catch (ex: java.lang.Exception) {
        ex.printStackTrace()
        Resource.Error(ex.message ?: "An error occurred.")
    }
}