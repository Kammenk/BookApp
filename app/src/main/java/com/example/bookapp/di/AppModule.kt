package com.example.bookapp.di

import android.app.Application
import androidx.room.Room
import com.example.bookapp.data.local.BooksDao
import com.example.bookapp.data.local.BooksDatabase
import com.example.bookapp.data.repository.BooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideBooksRepository(booksDao: BooksDao): BooksRepository =
        BooksRepository(booksDao)
}