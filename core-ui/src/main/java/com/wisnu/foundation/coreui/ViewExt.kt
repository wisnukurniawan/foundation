package com.wisnu.foundation.coreui

import android.view.View

fun View?.makeGone() {
    this?.visibility = View.GONE
}

fun View?.makeVisible() {
    this?.visibility = View.VISIBLE
}

fun View?.makeInvisible() {
    this?.visibility = View.INVISIBLE
}

