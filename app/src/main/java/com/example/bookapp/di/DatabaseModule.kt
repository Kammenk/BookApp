package com.example.bookapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.bookapp.data.local.BooksDao
import com.example.bookapp.data.local.BooksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideBookDao(database: BooksDatabase): BooksDao = database.bookDao()
    @Provides
    @Singleton
    fun provideBooksDatabase(@ApplicationContext appContext: Context): BooksDatabase =
        Room.databaseBuilder(
            appContext,
            BooksDatabase::class.java,
            "books.db"
        ).fallbackToDestructiveMigration().build()

}