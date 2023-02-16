package com.example.bookapp.presentation.features.booklist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.local.BookEntity
import com.example.bookapp.data.repository.BooksRepository
import com.example.bookapp.presentation.features.booklist.eventlisteners.BookListEventListeners
import com.example.bookapp.presentation.features.navigation.CustomNavigation
import com.example.bookapp.presentation.features.navigation.openAddBookScreen
import com.example.bookapp.presentation.features.navigation.openBookDetailsScreen
import com.example.bookapp.util.Resource
import com.example.bookapp.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class BookListViewModel @Inject constructor(private val booksRepository: BooksRepository) :
    ViewModel(), BookListEventListeners {

    private val _booksLiveData = MutableLiveData<List<BookEntity>?>()
    val booksLiveData: LiveData<List<BookEntity>?> = _booksLiveData

    private val _errorLiveData = MutableLiveData<String?>()
    val errorLiveData: LiveData<String?> = _errorLiveData

    private var _navigationLiveData: SingleLiveEvent<CustomNavigation> = SingleLiveEvent()
    val navigationLiveData: LiveData<CustomNavigation>
        get() = _navigationLiveData


    fun getAllBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            booksRepository.getAllBooks().let { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _booksLiveData.postValue(resource.data)
                    }
                    is Resource.Error -> {
                        _errorLiveData.postValue(resource.message)
                    }
                    else -> {}
                }
            }
        }
    }

    fun searchForBook(bookTitle: String) {
        viewModelScope.launch(Dispatchers.IO) {
            booksRepository.getBooksByName(bookTitle).let { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _booksLiveData.postValue(resource.data)
                    }
                    is Resource.Error -> {
                        _errorLiveData.postValue(resource.message)
                    }
                    else -> {}
                }
            }
        }
    }

    override fun onItemClicked(book: BookEntity) {
        _navigationLiveData.postValue(openBookDetailsScreen(book))
    }

    override fun onItemAdd() {
        _navigationLiveData.postValue(openAddBookScreen)
    }

    override fun searchBookByTitle(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            booksRepository.getBooksByName(title).let { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _booksLiveData.postValue(resource.data)
                    }
                    is Resource.Error -> {
                        _errorLiveData.postValue(resource.message)
                    }
                    else -> {}
                }
            }
        }
    }
}