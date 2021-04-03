package com.shawpoo.simplehencoder.app.layout.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.shawpoo.simplehencoder.app.ext.dp
import java.util.*

/**
 * @author: wuxiaopeng
 * @date: 2021/4/1
 * @desc: 自定义TextView，随机设置背景色和大小，用于测试 @see[TagLayout]
 */

private val COLORS = intArrayOf(Color.parseColor("#E91E63"), Color.parseColor("#673AB7"),
    Color.parseColor("#3F51B5"), Color.parseColor("#2196F3"), Color.parseColor("#009688"),
    Color.parseColor("#FF9800"), Color.parseColor("#FF5722"), Color.parseColor("#795548"))
private val TEXT_SIZES = intArrayOf(16, 22, 28)
private val CORNER_RADIUS = 4.dp
private val X_PADDING = 16.dp.toInt()
private val Y_PADDING = 8.dp.toInt()

class ColoredTextView(context: Context?, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val random = Random()

    init {
        setTextColor(Color.WHITE)
        textSize = TEXT_SIZES[random.nextInt(TEXT_SIZES.size)].toFloat()
        paint.color = COLORS[random.nextInt(COLORS.size)]
        setPadding(X_PADDING, Y_PADDING, X_PADDING, Y_PADDING)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), CORNER_RADIUS,
            CORNER_RADIUS, paint)
        super.onDraw(canvas)
    }

}