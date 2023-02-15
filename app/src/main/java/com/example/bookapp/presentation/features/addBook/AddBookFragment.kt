package com.example.bookapp.presentation.features.addBook

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.bookapp.base.BaseFragment
import com.example.bookapp.data.local.BookEntity
import com.example.bookapp.databinding.FragmentAddBookBinding
import com.example.bookapp.presentation.features.addBook.viewmodel.AddBookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBookFragment : BaseFragment<FragmentAddBookBinding>(FragmentAddBookBinding::inflate) {

    private val viewModel by viewModels<AddBookViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = viewModel

        binding.addBookBtn.setOnClickListener {
            viewModel.onBookAdd(
                BookEntity(
                    bookCover = binding.inputBookImageField.text.toString(),
                    bookTitle = binding.inputBookTitleField.text.toString(),
                    bookDescription = binding.inputBookDescriptionField.text.toString()
                )
            )
        }
    }
}