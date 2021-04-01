package com.shawpoo.simplehencoder.app.layout.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.shawpoo.simplehencoder.app.ext.dp

/**
 * @author: wuxiaopeng
 * @date: 2021/3/31
 * @desc:
 */
private val RADIUS = 100.dp
private val PADDING = 30.dp

class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = ((PADDING + RADIUS) * 2).toInt()
        val width = resolveSize(size, widthMeasureSpec)
        val height = resolveSize(size, heightMeasureSpec)

        // resolveSize()
        /*val specWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        val specWidthSize = MeasureSpec.getSize(widthMeasureSpec)
        val width = when (specWidthMode) {
            MeasureSpec.EXACTLY -> { // 精确值
                specWidthSize
            }
            MeasureSpec.AT_MOST -> { // 上限
                if (size > specWidthSize) {
                    specWidthSize
                } else {
                    size
                }
            }
            MeasureSpec.UNSPECIFIED -> { // 不限制
                size
            }
            else -> size
        }*/
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(PADDING + RADIUS, PADDING + RADIUS, RADIUS, paint)
    }

}