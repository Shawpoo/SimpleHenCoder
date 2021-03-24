package com.shawpoo.simplehencoder.app.xfermode.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.shawpoo.simplehencoder.app.ext.dp

/**
 * @author: wuxiaopeng
 * @date: 2021/3/23
 * @desc: Xfermode中PorterDuffXfermode完全使用解析
 *        官方文档：https://developer.android.google.cn/reference/android/graphics/PorterDuff.Mode?hl=en
 */


class XfermodeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var xfermode = PorterDuffXfermode(PorterDuff.Mode.DARKEN)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bounds = RectF(150f.dp, 50f.dp, 300f.dp, 200f.dp)

    private val circleBitmap =
        Bitmap.createBitmap(150f.dp.toInt(), 150f.dp.toInt(), Bitmap.Config.ARGB_8888)
    private val squareBitmap =
        Bitmap.createBitmap(150f.dp.toInt(), 150f.dp.toInt(), Bitmap.Config.ARGB_8888)

    init {
        val canvas = Canvas(circleBitmap)
        paint.color = Color.parseColor("#DB1860")
        canvas.drawOval(50f.dp, 0f.dp, 150f.dp, 100f.dp, paint)
        paint.color = Color.parseColor("#2196F3")
        canvas.setBitmap(squareBitmap)
        canvas.drawRect(0f.dp, 50f.dp, 100f.dp, 150f.dp, paint)
    }

    public fun setXfermode(mode: PorterDuff.Mode) {
        xfermode = PorterDuffXfermode(mode)
    }

    override fun onDraw(canvas: Canvas) {
        val count = canvas.saveLayer(bounds, null)
        canvas.drawBitmap(circleBitmap, 150f.dp, 50f.dp, paint)

        paint.xfermode = xfermode // 设置离屏缓冲
        canvas.drawBitmap(squareBitmap, 150f.dp, 50f.dp, paint)

        paint.xfermode = null
        canvas.restoreToCount(count)
    }

}