package com.shawpoo.simplehencoder.app.drawing.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.shawpoo.simplehencoder.app.ext.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author: wuxiaopeng
 * @date: 2021/3/21
 * @desc: 饼图
 */
private val RADIUS = 150f.dp
private val ANGLES = floatArrayOf(60f, 90f, 150f, 60f)
private val COLORS = listOf(Color.GREEN, Color.RED, Color.YELLOW, Color.BLUE)
private val OFFSET_LENGTH = 20f.dp

class PieView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    }

    override fun onDraw(canvas: Canvas) {
        var startAngle = 0f
        for ((index, angle) in ANGLES.withIndex()) {
            paint.color = COLORS[index]
            if (index == 2) {
                canvas.save()
                canvas.translate(OFFSET_LENGTH * cos(makeToRadians(angle, startAngle)),
                    OFFSET_LENGTH * sin(makeToRadians(angle, startAngle)))
            }
            canvas.drawArc(width / 2f - RADIUS, height / 2f - RADIUS, width / 2f + RADIUS,
                height / 2f + RADIUS, startAngle, angle, true, paint)
            startAngle += angle
            if (index == 2) {
                canvas.restore()
            }
        }
    }

    private fun makeToRadians(angle: Float, startAngle: Float) =
        Math.toRadians(startAngle + angle / 2f.toDouble()).toFloat()

}