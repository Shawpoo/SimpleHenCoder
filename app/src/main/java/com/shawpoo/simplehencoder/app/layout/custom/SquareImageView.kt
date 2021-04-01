package com.shawpoo.simplehencoder.app.layout.custom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.min

/**
 * @author: wuxiaopeng
 * @date: 2021/3/31
 * @desc:
 */
class SquareImageView(context: Context?, attrs: AttributeSet?) :
    AppCompatImageView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // measuredWidth 测量的期望尺寸
        // width 实际的尺寸
        val size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

//    override fun layout(l: Int, t: Int, r: Int, b: Int) {
//        val width = r - l
//        val height = b - t
//        val size = min(width, height)
//        super.layout(l, t, l + size, t+ size)
//    }

}