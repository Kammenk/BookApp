package com.example.bookapp.presentation.features.navigation

import com.example.bookapp.data.local.BookEntity

sealed class CustomNavigation

object openAddBookScreen: CustomNavigation()
class openBookDetailsScreen(val book: BookEntity): CustomNavigation()