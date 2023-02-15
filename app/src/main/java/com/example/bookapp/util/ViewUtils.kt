package com.example.bookapp.util

import android.view.View
import com.example.bookapp.presentation.snackbar.CustomSnackBar
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(
    customSnackBar: CustomSnackBar,
    duration: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar.make(this, customSnackBar.stringResource, duration).show()
}