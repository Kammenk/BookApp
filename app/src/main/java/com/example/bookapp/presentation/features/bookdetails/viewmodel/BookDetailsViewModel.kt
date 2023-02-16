package com.example.bookapp.presentation.features.bookdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.local.BookEntity
import com.example.bookapp.data.repository.BooksRepository
import com.example.bookapp.presentation.features.bookdetails.eventlisteners.BookDetailsEventListeners
import com.example.bookapp.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class BookDetailsViewModel @Inject constructor(private val booksRepository: BooksRepository) :
    ViewModel(), BookDetailsEventListeners {

    private var _navigationLiveData: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val navigationLiveData: LiveData<Boolean>
        get() = _navigationLiveData

    override fun onDeleteBook(book: BookEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            booksRepository.deleteBook(book)
            _navigationLiveData.postValue(true)
        }
    }
}