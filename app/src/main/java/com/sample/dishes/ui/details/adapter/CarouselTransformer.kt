package com.sample.dishes.ui.details.adapter

import android.content.Context
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.sample.dishes.R

class CarouselTransformer (val context: Context) :
    ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val pageMarginPx = context.resources.getDimensionPixelOffset(R.dimen._3ssp)
        val offsetPx = context.resources.getDimensionPixelOffset(R.dimen._34sdp)

        page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
        val offset = position * -(2 * offsetPx + pageMarginPx)
        page.translationX = offset
    }
}