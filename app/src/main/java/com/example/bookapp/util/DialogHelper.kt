package com.example.bookapp.util

import android.content.Context
import com.example.bookapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogHelper {
    companion object {
        fun Context.onError(message: String) {
            MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.error_title))
                .setMessage(message)
                .setPositiveButton(getString(R.string.okay)) { dialog, _ ->
                    dialog.dismiss()
                }.show()
        }
    }
}