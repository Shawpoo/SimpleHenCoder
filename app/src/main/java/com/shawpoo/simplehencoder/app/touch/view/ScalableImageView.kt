package com.shawpoo.simplehencoder.app.touch.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.shawpoo.simplehencoder.app.R
import com.shawpoo.simplehencoder.app.ext.dp
import kotlin.math.max
import kotlin.math.min

/**
 * @author: wuxiaopeng
 * @date: 2021/4/4
 * @desc: 双向滑动的ScalableImageView
 */
private val IMAGE_SIZE = 300.dp
private const val EXTRA_SCALE_FACTOR = 1.5f

class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(IMAGE_SIZE.toInt())
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f
    private var offsetX = 0f
    private var offsetY = 0f
    private var smallScale = 0f
    private var bigScale = 0f
    private var appGestureListener = AppGestureListener()
    private var appScaleGestureListener = AppScaleGestureListener()
    private var appFlingRunner = AppFlingRunner()
    private var gestureDetector = GestureDetectorCompat(context, appGestureListener)
    private var scaleGestureDetector = ScaleGestureDetector(context, appScaleGestureListener)
    private var big = false
    private var currentScale = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val scalaAnimator: ObjectAnimator =
        ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale)
    private val scroller = OverScroller(context)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        originalOffsetX = (width - IMAGE_SIZE) / 2f
        originalOffsetY = (height - IMAGE_SIZE) / 2f

        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            smallScale = width / bitmap.width.toFloat()
            bigScale = height / bitmap.height.toFloat() * EXTRA_SCALE_FACTOR
        } else {
            bigScale = width / bitmap.width.toFloat()
            smallScale = height / bitmap.height.toFloat() * EXTRA_SCALE_FACTOR
        }
        currentScale = smallScale
        scalaAnimator.setFloatValues(smallScale, bigScale)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        if (!scaleGestureDetector.isInProgress) {
            gestureDetector.onTouchEvent(event)
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val scalaFraction = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.translate(offsetX * scalaFraction, offsetY * scalaFraction)
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
    }

    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.mipmap.ic_default_avatar, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.mipmap.ic_default_avatar, options)
    }

    private fun fixOffsets() {
        offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
        offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)
        offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
        offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)
    }

    inner class AppGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            return true // 拿下事件流的主动权
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float,
            velocityY: Float): Boolean { // 快滑的松手的时候执行。松手后利用惯性滑动的时候执行
            if (big) {
                var x = ((bitmap.width * bigScale - width) / 2).toInt()
                var y = ((bitmap.height * bigScale - height) / 2).toInt()
                scroller.fling(offsetX.toInt(), offsetY.toInt(), velocityX.toInt(),
                    velocityY.toInt(), -x, x, -y, y, 40.dp.toInt(), 40.dp.toInt())
                ViewCompat.postOnAnimation(this@ScalableImageView, appFlingRunner)
            }
            return false
        }

        override fun onScroll(downEvent: MotionEvent?, currentEvent: MotionEvent?, distanceX: Float,
            distanceY: Float): Boolean {
            if (big) {
                offsetX -= distanceX
                offsetY -= distanceY
                fixOffsets()
                invalidate()
            }
            return false
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            big = !big
            if (big) {
                offsetX = (e.x - width / 2f) * (1 - bigScale / smallScale)
                offsetY = (e.y - height / 2f) * (1 - bigScale / smallScale)
                fixOffsets()
                scalaAnimator.start()
            } else {
                scalaAnimator.reverse()
            }
            return true // 返回值无意义
        }
    }

    inner class AppScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {
        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            offsetX = (detector.focusX - width / 2f) * (1 - bigScale / smallScale)
            offsetY = (detector.focusY - height / 2f) * (1 - bigScale / smallScale)
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val tempCurrentScale = currentScale * detector.scaleFactor
            return if (tempCurrentScale < smallScale || tempCurrentScale > bigScale) {
                false
            } else {
                currentScale *= detector.scaleFactor
                true
            }
        }

    }

    inner class AppFlingRunner : Runnable {
        override fun run() {
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.currX.toFloat()
                offsetY = scroller.currY.toFloat()
                invalidate()
                ViewCompat.postOnAnimation(this@ScalableImageView, this)
            }
        }
    }

}