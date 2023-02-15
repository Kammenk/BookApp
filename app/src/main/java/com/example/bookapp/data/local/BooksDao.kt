package com.example.bookapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBook(book: BookEntity)

    @Delete
    suspend fun deleteBook(book: BookEntity)

    @Query("SELECT * FROM book")
    suspend fun getAllBooks(): List<BookEntity>

    @Query("SELECT * FROM book WHERE id == :bookId")
    suspend fun getBookDetails(bookId: Int): BookEntity
}