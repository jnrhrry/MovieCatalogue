package com.januar.submission2jetpack.utils

import android.view.View
import android.widget.ProgressBar
import com.google.android.material.snackbar.Snackbar

fun ProgressBar.show(){
    visibility = View.VISIBLE
}

fun ProgressBar.gone(){
    visibility = View.GONE
}

fun View.snackBar(message: String){
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackBar ->
        snackBar.setAction("OK") {
            snackBar.dismiss()
        }
    }.show()
}