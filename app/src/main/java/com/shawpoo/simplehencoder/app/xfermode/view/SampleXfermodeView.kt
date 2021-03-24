package com.shawpoo.simplehencoder.app.xfermode.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.shawpoo.simplehencoder.app.drawing.view.px

/**
 * @author: wuxiaopeng
 * @date: 2021/3/23
 * @desc: Xfermode中PorterDuffXfermode完全使用解析
 *        官方文档：https://developer.android.google.cn/reference/android/graphics/PorterDuff.Mode?hl=en
 */

private const val RADIUS = 22f

class SampleXfermodeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var xfermode = PorterDuffXfermode(PorterDuff.Mode.DARKEN)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private lateinit var bounds: RectF

    // +1 目的是防止转int和float有略微差异导致图形不全
    private val circleBitmap =
        Bitmap.createBitmap((RADIUS * 3 + 1).px.toInt(), (RADIUS * 3 + 1).px.toInt(), Bitmap.Config.ARGB_8888)
    private val squareBitmap =
        Bitmap.createBitmap((RADIUS * 3 + 1).px.toInt(), (RADIUS * 3 + 1).px.toInt(), Bitmap.Config.ARGB_8888)

    init {
        val canvas = Canvas(circleBitmap)
        paint.color = Color.parseColor("#DB1860")
        canvas.drawOval((RADIUS * 1).px, 0f.px, (RADIUS * 3).px, (RADIUS * 2).px, paint)
        paint.color = Color.parseColor("#2196F3")
        canvas.setBitmap(squareBitmap)
        canvas.drawRect(0f.px, (RADIUS * 1).px, (RADIUS * 2).px, (RADIUS * 3).px, paint)
    }

    fun setXfermode(mode: PorterDuff.Mode) {
        xfermode = PorterDuffXfermode(mode)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        bounds = RectF(0f, 0f, width.toFloat(), height.toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        val count = canvas.saveLayer(bounds, null)
        canvas.drawBitmap(circleBitmap, (RADIUS * 2).px, (RADIUS * 2).px, paint)

        paint.xfermode = xfermode // 设置离屏缓冲
        canvas.drawBitmap(squareBitmap, (RADIUS * 2).px, (RADIUS * 2).px, paint)

        paint.xfermode = null
        canvas.restoreToCount(count)
    }

}