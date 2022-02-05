package com.android.jpchallenge.utility

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnack(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).show()
}