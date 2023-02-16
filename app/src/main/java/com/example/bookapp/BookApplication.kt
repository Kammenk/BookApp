package com.example.bookapp

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.example.bookapp.binding.AppDataBindingComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BookApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //Initialize Data Binding Adapter
        DataBindingUtil.setDefaultComponent(AppDataBindingComponent())
    }
}