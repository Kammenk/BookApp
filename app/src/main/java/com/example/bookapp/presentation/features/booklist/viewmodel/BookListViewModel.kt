package com.example.bookapp.presentation.features.booklist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.local.BookEntity
import com.example.bookapp.data.repository.BooksRepository
import com.example.bookapp.presentation.features.booklist.eventlisteners.BookListEventListeners
import com.example.bookapp.presentation.snackbar.CustomSnackBar
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

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private var _customSnackBarLiveData: SingleLiveEvent<CustomSnackBar> = SingleLiveEvent()
    val customSnackBarLiveData: LiveData<CustomSnackBar>
        get() = _customSnackBarLiveData

    private var _navigationLiveData: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val navigationLiveData: LiveData<Boolean>
        get() = _navigationLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            booksRepository.getAllBooks().let { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _booksLiveData.postValue(resource.data)
                    }
                    is Resource.Error -> {
                        _errorLiveData.postValue(resource.message)
                    }
                    else -> {
                        _loading.postValue(true)
                    }
                }
            }
        }
    }

    override fun onItemClicked() {
        TODO("Not yet implemented")
    }

    override fun onItemAdd() {
        _navigationLiveData.value = true
    }

}