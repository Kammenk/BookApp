package com.example.bookapp.presentation.features.booklist

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bookapp.MainActivity
import com.example.bookapp.base.BaseFragment
import com.example.bookapp.databinding.FragmentBookListBinding
import com.example.bookapp.presentation.features.booklist.adapter.BooksAdapter
import com.example.bookapp.presentation.features.booklist.viewmodel.BookListViewModel
import com.example.bookapp.presentation.features.navigation.openAddBookScreen
import com.example.bookapp.presentation.features.navigation.openBookDetailsScreen
import com.example.bookapp.util.DialogHelper.Companion.onError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookListFragment : BaseFragment<FragmentBookListBinding>(FragmentBookListBinding::inflate) {

    private val viewModel by viewModels<BookListViewModel>()
    private val booksAdapter: BooksAdapter by lazy {
        BooksAdapter(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = viewModel
        initSearchViewListener()
        initRecyclerView()
        observeLiveData()
        observeNavigation()
        observerErrorLiveData()
        viewModel.getAllBooks()
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun initRecyclerView() {
        binding.bookList.apply {
            setHasFixedSize(true)
            adapter = booksAdapter
        }
    }

    private fun observeNavigation() {
        viewModel.navigationLiveData.observe(viewLifecycleOwner) { navigationType ->
            findNavController().navigate(
                when (navigationType) {
                    is openAddBookScreen -> {
                        BookListFragmentDirections.addBook()
                    }
                    is openBookDetailsScreen -> {
                        BookListFragmentDirections.seeDetails(navigationType.book)
                    }
                }
            )
        }
    }

    private fun initSearchViewListener() {
        binding.searchView.apply {
            setOnCloseListener {
                if (this.query.isEmpty()) {
                    viewModel.getAllBooks()
                }
                false
            }
            setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        viewModel.searchForBook(it)
                    }
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    return false
                }
            })
        }

    }

    private fun observeLiveData() {
        viewModel.booksLiveData.observe(viewLifecycleOwner) {
            binding.viewModel = viewModel
            booksAdapter.submitList(it)
        }
    }

    private fun observerErrorLiveData() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            requireContext().onError(it.toString())
        }
    }
}