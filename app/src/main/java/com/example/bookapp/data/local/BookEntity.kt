package com.example.bookapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class BookEntity(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo(name = "book_cover")
    val bookCover: String,
    @ColumnInfo(name = "book_title")
    val bookTitle: String,
    @ColumnInfo(name = "book_description")
    val bookDescription: String
)