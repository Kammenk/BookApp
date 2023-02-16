package com.example.bookapp.presentation.features.addBook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.local.BookEntity
import com.example.bookapp.data.repository.BooksRepository
import com.example.bookapp.presentation.features.addBook.eventlisteners.AddBookEventListener
import com.example.bookapp.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class AddBookViewModel @Inject constructor(private val booksRepository: BooksRepository) :
    ViewModel(), AddBookEventListener {

    private val _errorLiveData = MutableLiveData<String?>()
    val errorLiveData: LiveData<String?> = _errorLiveData

    private var _navigationLiveData: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val navigationLiveData: LiveData<Boolean>
        get() = _navigationLiveData

    override fun onBookAdd(book: BookEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            booksRepository.addBook(book).let {
                if (it > 0) {
                    _navigationLiveData.postValue(true)
                } else {
                    _errorLiveData.postValue("An error occurred")
                }
            }
        }
    }
}