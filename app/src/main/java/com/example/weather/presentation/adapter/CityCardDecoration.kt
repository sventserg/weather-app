package com.example.weather.presentation.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Decoration for recycler view with spacing between recycler items
 */

class CityCardDecoration (private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.adapter?.let {
            outRect.bottom = when (parent.getChildAdapterPosition(view)) {
                RecyclerView.NO_POSITION, it.itemCount - 1 -> 0
                else -> this.spacing
            }
        }
    }
}