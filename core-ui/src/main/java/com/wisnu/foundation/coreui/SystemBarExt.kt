package com.wisnu.foundation.coreui

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

private val translucentBarColor = Color.parseColor("#80000000")

fun Window.toggleLightStatusBars(light: Boolean? = null) {
    WindowCompat.getInsetsController(this, decorView).let {
        val l = light ?: !it.isAppearanceLightStatusBars
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            it.isAppearanceLightStatusBars = false
            statusBarColor = if (l) translucentBarColor else Color.TRANSPARENT
        } else
            it.isAppearanceLightStatusBars = l
    }
}

fun View.statusNavigationBarPadding() {
    doOnApplyWindowInsets { view, windowInsets, initialPadding ->
        val insets = windowInsets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars())
        view.setPadding(
            initialPadding.left,
            initialPadding.top + insets.top,
            initialPadding.right,
            initialPadding.bottom + insets.bottom,
        )
    }
}

internal fun View.doOnApplyWindowInsets(f: (View, WindowInsetsCompat, InitialPadding) -> Unit) {
    val initialPadding = recordInitialPaddingForView(this)
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        f(v, insets, initialPadding)
        insets
    }
    requestApplyInsetsWhenAttached()
}

internal data class InitialPadding(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int
)

internal fun recordInitialPaddingForView(view: View) = InitialPadding(
    view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom
)

internal fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}
