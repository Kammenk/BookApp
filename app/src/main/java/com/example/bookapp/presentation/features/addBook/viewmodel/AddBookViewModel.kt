package com.example.bookapp.presentation.features.addBook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.local.BookEntity
import com.example.bookapp.data.repository.BooksRepository
import com.example.bookapp.presentation.features.addBook.eventlisteners.AddBookEventListener
import com.example.bookapp.presentation.snackbar.CustomSnackBar
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

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private var _customSnackBarLiveData: SingleLiveEvent<CustomSnackBar> = SingleLiveEvent()
    val customSnackBarLiveData: LiveData<CustomSnackBar>
        get() = _customSnackBarLiveData

    private var _navigationLiveData: SingleLiveEvent<BookEntity> = SingleLiveEvent()
    val navigationLiveData: LiveData<BookEntity>
        get() = _navigationLiveData

    override fun onBookAdd(book: BookEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            booksRepository.addBook(book)
        }
    }
}