package com.shawpoo.simplehencoder.app.text.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.shawpoo.simplehencoder.app.R
import com.shawpoo.simplehencoder.app.ext.dp

/**
 * @author: wuxiaopeng
 * @date: 2021/3/24
 * @desc: 文字的测量
 * top、ascent、baseline、descent、bottom
 * 1、文字的纵向居中对齐
 * 2、文字的贴边对齐
 * 3、多行绘制(图文混排)，@see[MultilineTextView]
 */
private val CIRCLE_COLOR = Color.parseColor("#90A4AE")
private val HIGHLIGHT_COLOR = Color.parseColor("#FF4081")
private val RING_WIDTH = 20.dp
private val RADIUS = 150.dp

class SportView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 100.dp
        typeface = ResourcesCompat.getFont(context, R.font.font) // 设置字体，对比不同字体的差异
        textAlign = Paint.Align.CENTER
    }
    private val bounds = Rect()
    private val fontMetrics = Paint.FontMetrics()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 绘制环
        paint.style = Paint.Style.STROKE
        paint.color = CIRCLE_COLOR
        paint.strokeWidth = RING_WIDTH
        canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)

        // 绘制圆弧
        paint.color = HIGHLIGHT_COLOR
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(width / 2f - RADIUS, height / 2f - RADIUS, width / 2f + RADIUS,
            height / 2f + RADIUS, -90f, 225f, false, paint)

        // 绘制文字
        paint.style = Paint.Style.FILL
        paint.getTextBounds("ab88", 0, "ab88".length, bounds) // 获取文字测量的偏移量，适合静态文字的测量
        canvas.drawText("ab88", width / 2f, height / 2f - (bounds.top + bounds.bottom) / 2f, paint)
        //        paint.getFontMetrics(fontMetrics) // 适合动态文字测量
        //        canvas.drawText("aAg88", width / 2f,
        //        height / 2f - (fontMetrics.top + fontMetrics.bottom) / 2f, paint)

        // 绘制文字2
        paint.textAlign = Paint.Align.LEFT
        //        paint.getFontMetrics(fontMetrics)
        //        canvas.drawText("abab", 0f, -fontMetrics.top, paint)
        paint.getTextBounds("abab", 0, "abab".length, bounds)
        canvas.drawText("abab", -bounds.left.toFloat(), -bounds.top.toFloat(), paint)

        // 绘制文字3
        paint.textSize = 15.dp
        paint.getTextBounds("abab", 0, "abab".length, bounds)
        canvas.drawText("abab", 0f, -bounds.top.toFloat(), paint)
    }

}