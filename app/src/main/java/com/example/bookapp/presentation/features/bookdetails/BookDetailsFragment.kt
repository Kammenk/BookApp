package com.example.bookapp.presentation.features.bookdetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bookapp.MainActivity
import com.example.bookapp.base.BaseFragment
import com.example.bookapp.databinding.FragmentBookDetailsBinding
import com.example.bookapp.presentation.features.bookdetails.viewmodel.BookDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailsFragment :
    BaseFragment<FragmentBookDetailsBinding>(FragmentBookDetailsBinding::inflate) {

    private val viewModel by viewModels<BookDetailsViewModel>()
    private val args by navArgs<BookDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            bookDetails = args.bookDetails
            listener = viewModel
        }
        observeNavigation()
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun observeNavigation() {
        viewModel.navigationLiveData.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }
}