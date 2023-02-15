package com.example.bookapp.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.bookapp.R
import com.example.bookapp.util.DataBoundListAdapter

object AppBindingAdapter {

    /**
     * Adapter for loading an image from a url
     */
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView?, imageUrl: String?) {
        view?.let { it ->
            Glide.with(it.context)
                .load(imageUrl)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        view.post(Runnable {
                            view
                                .setImageDrawable(
                                    ContextCompat.getDrawable(
                                        view.context,
                                        R.drawable.ic_launcher_foreground
                                    )
                                )
                        })
                        view.scaleType = ImageView.ScaleType.CENTER
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean = false

                })
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .centerCrop()
                )
                .into(view)
        }
    }

    @BindingAdapter("setAdapter")
    fun setAdapter(
        recyclerView: RecyclerView,
        adapter: DataBoundListAdapter<Any, ViewDataBinding>?
    ) {
        adapter?.let {
            recyclerView.adapter = it
        }
    }

    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("submitList")
    fun submitList(recyclerView: RecyclerView, list: List<Any>?) {
        submitList(recyclerView, list)
    }

}