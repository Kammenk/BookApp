package com.example.bookapp.binding

import androidx.databinding.DataBindingComponent

class AppDataBindingComponent : DataBindingComponent {
    override fun getAppBindingAdapter(): AppBindingAdapter = AppBindingAdapter
}