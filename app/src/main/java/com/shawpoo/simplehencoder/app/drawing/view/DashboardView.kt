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
 * @date: 2021/3/20
 * @desc: 仪表盘(表盘+指针)
 */
private const val OPEN_ANGLE = 120f
private val RADIUS = 150f.dp // 表盘半径
private val LENGTH = 120f.dp // 指针长度
private val DASH_WIDTH = 2f.dp // 刻度粗细
private val DASH_LENGTH = 8f.dp // 刻度长度

private const val MARK = 8

class DashboardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val dashPath = Path()
    private lateinit var pathEffect: PathEffect

    init {
        paint.strokeWidth = 3f.dp
        paint.style = Paint.Style.STROKE
        dashPath.addRect(0f, 0f, DASH_WIDTH, DASH_LENGTH, Path.Direction.CCW)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addArc(width / 2f - RADIUS, height / 2f - RADIUS, width / 2f + RADIUS,
            height / 2f + RADIUS, 90 + OPEN_ANGLE / 2f, 360 - OPEN_ANGLE)
        val pathMeasure = PathMeasure(path, false)
        pathEffect = PathDashPathEffect(dashPath, (pathMeasure.length - DASH_WIDTH) / 20f, 0f,
            PathDashPathEffect.Style.ROTATE)
    }

    override fun onDraw(canvas: Canvas) {
        // 画弧
        canvas.drawPath(path, paint)

        // 画刻度
        paint.pathEffect = pathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null

        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        // 画指针
        canvas.drawLine(width / 2f, height / 2f,
            width / 2f + LENGTH * cos(makeToRadians(MARK)).toFloat(),
            height / 2f + LENGTH * sin(makeToRadians(MARK)).toFloat(), paint)

        canvas.drawCircle(width / 2f, height / 2f, 15f, paint)
    }

    private fun makeToRadians(mark: Int) =
        Math.toRadians((90 + OPEN_ANGLE / 2f + (360 - OPEN_ANGLE) / 20f * mark).toDouble())

}