package com.example.bookapp.presentation.features.addBook

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookapp.MainActivity
import com.example.bookapp.R
import com.example.bookapp.base.BaseFragment
import com.example.bookapp.data.local.BookEntity
import com.example.bookapp.databinding.FragmentAddBookBinding
import com.example.bookapp.presentation.features.addBook.viewmodel.AddBookViewModel
import com.example.bookapp.util.DialogHelper.Companion.onError
import com.example.bookapp.util.ValidationUtils.isTitleOrDescriptionValid
import com.example.bookapp.util.ValidationUtils.isURLProvided
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBookFragment : BaseFragment<FragmentAddBookBinding>(FragmentAddBookBinding::inflate) {

    private val viewModel by viewModels<AddBookViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = viewModel

        binding.addBookBtn.setOnClickListener {
            val coverURL = binding.inputBookImageField.text.toString()
            val title = binding.inputBookTitleField.text.toString()
            val description = binding.inputBookDescriptionField.text.toString()

            if (validateFields(coverURL, title, description)) {
                viewModel.onBookAdd(
                    BookEntity(
                        bookCover = coverURL,
                        bookTitle = title,
                        bookDescription = description
                    )
                )
            }
        }
        observeNavigation()
        observerErrorLiveData()
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun validateFields(bookCover: String, title: String, description: String): Boolean {
        var canProceed = true

        binding.apply {
            if (!isURLProvided(bookCover)) {
                bookImageField.error = getString(R.string.book_cover_requirements)
                canProceed = false
            } else {
                bookImageField.error = null
            }

            if (isTitleOrDescriptionValid(title)) {
                bookTitleField.error = getString(R.string.title_and_description_requirements)
                canProceed = false
            } else {
                bookTitleField.error = null
            }

            if (isTitleOrDescriptionValid(description)) {
                bookDescriptionField.error = getString(R.string.title_and_description_requirements)
                canProceed = false
            } else {
                bookDescriptionField.error = null
            }
        }
        return canProceed
    }

    private fun observeNavigation() {
        viewModel.navigationLiveData.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }

    private fun observerErrorLiveData() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            requireContext().onError(it.toString())
        }
    }
}