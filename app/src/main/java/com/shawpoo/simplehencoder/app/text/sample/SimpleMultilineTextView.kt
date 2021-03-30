package com.shawpoo.simplehencoder.app.text.sample

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.shawpoo.simplehencoder.app.R
import com.shawpoo.simplehencoder.app.ext.dp

/**
 * @author: wuxiaopeng
 * @date: 2021/3/25
 * @desc:
 */
private val IMAGE_SIZE = 20.dp

class SimpleMultilineTextView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var text =
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 13.dp
    }
    private val paint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 18.dp
    }
    private var bitmap = getAvatar(IMAGE_SIZE.toInt())
    private val fontMetrics = Paint.FontMetrics()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        paint.getFontMetrics(fontMetrics)

        val measureWidth = floatArrayOf(0f)
        var start = 0
        var count: Int
        var verticalOffset = -fontMetrics.top
        var horizontalOffset: Float
        var maxWidth: Float
        var lineCount = 0
        while (start < text.length) {
            if (verticalOffset + fontMetrics.top > IMAGE_SIZE) {
                horizontalOffset = 0f
                maxWidth = width.toFloat()
            } else {
                horizontalOffset = IMAGE_SIZE + 3.dp
                maxWidth = width.toFloat() - IMAGE_SIZE
            }
            count = paint.breakText(text, start, text.length, true, maxWidth, measureWidth)
            if (lineCount == 1 && (start + count < text.length)) {
                var tempText = text.substring(start, start + count - 1) + "..."
                canvas.drawText(tempText, 0, tempText.length, horizontalOffset, verticalOffset, paint)
                break
            } else {
                canvas.drawText(text, start, start + count, horizontalOffset, verticalOffset, paint)
            }
            start += count
            lineCount++
            verticalOffset += paint.fontSpacing
        }
    }

    fun setBitmap(oldBitmap: Bitmap) {
        bitmap =
            Bitmap.createScaledBitmap(oldBitmap, IMAGE_SIZE.toInt(), IMAGE_SIZE.toInt(), true)
        invalidate()
    }

    fun setText(str: String) {
        text = str
        invalidate()
    }

    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.twitter, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.twitter, options)
    }

}