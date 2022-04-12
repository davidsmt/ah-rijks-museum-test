package com.ahrijksmuseum.view.utils

import android.content.Context
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

fun View.visible(isVisible: Boolean, ifNot: Int = View.GONE) {
    visibility = if (isVisible) View.VISIBLE else ifNot
}

fun DividerItemDecoration.withCustomDrawable(
    context: Context,
    @DrawableRes drawableId: Int
): DividerItemDecoration {
    ContextCompat.getDrawable(context, drawableId)?.let {
        setDrawable(it)
    }
    return this
}

fun RecyclerView.clearItemDecorations() {
    while (itemDecorationCount > 0) {
        removeItemDecorationAt(0)
    }
}