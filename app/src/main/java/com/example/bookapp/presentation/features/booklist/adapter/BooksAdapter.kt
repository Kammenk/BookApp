package com.example.bookapp.presentation.features.booklist.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.bookapp.R
import com.example.bookapp.data.local.BookEntity
import com.example.bookapp.databinding.LayoutBookItemBinding
import com.example.bookapp.presentation.features.booklist.eventlisteners.BookListEventListeners
import com.example.bookapp.util.DataBoundListAdapter

class BooksAdapter(private val listener: BookListEventListeners) : DataBoundListAdapter<BookEntity,
        LayoutBookItemBinding>(BookItemDiffUtil()) {

    override fun createBinding(
        parent: ViewGroup,
        viewType: Int
    ): LayoutBookItemBinding = createViewDataBinding(
        R.layout.layout_book_item,
        parent
    )

    override fun bind(binding: LayoutBookItemBinding, item: BookEntity, position: Int) {
        binding.book = item
        binding.listener = listener
    }

    private fun createViewDataBinding(
        @LayoutRes layout: Int,
        parent: ViewGroup
    ): LayoutBookItemBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
}

class BookItemDiffUtil : DiffUtil.ItemCallback<BookEntity>() {
    override fun areItemsTheSame(
        oldItem: BookEntity,
        newItem: BookEntity
    ): Boolean = oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: BookEntity,
        newItem: BookEntity
    ): Boolean = oldItem == newItem

}