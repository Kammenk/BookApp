package com.example.bookapp.presentation.features.booklist

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bookapp.base.BaseFragment
import com.example.bookapp.databinding.FragmentBookListBinding
import com.example.bookapp.presentation.features.booklist.adapter.BooksAdapter
import com.example.bookapp.presentation.features.booklist.viewmodel.BookListViewModel
import com.example.bookapp.util.showSnackBar
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
        observeSnackBar()
        observeNavigation()
    }

    private fun initRecyclerView() {
        binding.bookList.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                GridLayoutManager.VERTICAL
            ).apply {
                gapStrategy =
                    StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            }
            setHasFixedSize(true)
            //addOnScrollListener(addRecyclerViewScrollListener())
            adapter = booksAdapter
        }
    }

    private fun observeNavigation() {
        viewModel.navigationLiveData.observe(requireActivity()) {
            findNavController().navigate(BookListFragmentDirections.addBook())
        }
    }

    private fun initSearchViewListener() {
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    //viewModel.searchImages(it)
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })
    }

    private fun observeSnackBar() {
        viewModel.customSnackBarLiveData.observe(requireActivity()) {
            binding.root.showSnackBar(it)
        }
    }

    private fun observeLiveData() {
        viewModel.booksLiveData.observe(requireActivity()) {
            //binding.uiModel = it
            booksAdapter.submitList(it)
        }
    }
}