package com.shawpoo.simplehencoder.app.clipandcamera.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.shawpoo.simplehencoder.app.R
import com.shawpoo.simplehencoder.app.ext.dp

/**
 * @author: wuxiaopeng
 * @date: 2021/3/23
 * @desc: 范围裁切和几何变换
 */

private val IMAGE_WIDTH = 200f.dp
private val IMAGE_PADDING = 100f.dp

class CameraView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(IMAGE_WIDTH.toInt())
    private val clipped = Path().apply {
        addOval(IMAGE_PADDING, IMAGE_PADDING, IMAGE_PADDING + IMAGE_WIDTH,
            IMAGE_PADDING + IMAGE_WIDTH, Path.Direction.CCW) // 椭圆
    }

    private val camera = Camera()

    init {
        camera.rotateX(30f)
        camera.setLocation(0f, 0f, -6 * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        //        canvas.clipRect(IMAGE_PADDING, IMAGE_PADDING, IMAGE_PADDING + IMAGE_WIDTH / 2,
        //            IMAGE_PADDING + IMAGE_WIDTH / 2) // 裁剪矩形
        //        canvas.clipPath(clipped) // 裁剪path，与xfermode相比，有锯齿

        // 上半部分
        canvas.save()
        canvas.translate(IMAGE_PADDING + IMAGE_WIDTH / 2, IMAGE_PADDING + IMAGE_WIDTH / 2)
        canvas.rotate(-30f)
        canvas.clipRect(-IMAGE_WIDTH, -IMAGE_WIDTH, IMAGE_WIDTH, 0f)
        canvas.rotate(30f)
        canvas.translate(-(IMAGE_PADDING + IMAGE_WIDTH / 2), -(IMAGE_PADDING + IMAGE_WIDTH / 2))
        canvas.drawBitmap(bitmap, IMAGE_PADDING, IMAGE_PADDING, paint)
        canvas.restore()

        // 下半部分
        canvas.save()
        canvas.translate(IMAGE_PADDING + IMAGE_WIDTH / 2, IMAGE_PADDING + IMAGE_WIDTH / 2)
        canvas.rotate(-30f)
        camera.applyToCanvas(canvas)
        canvas.clipRect(-IMAGE_WIDTH, 0f, IMAGE_WIDTH, IMAGE_WIDTH)
        canvas.rotate(30f)
        canvas.translate(-(IMAGE_PADDING + IMAGE_WIDTH / 2), -(IMAGE_PADDING + IMAGE_WIDTH / 2))
        canvas.drawBitmap(bitmap, IMAGE_PADDING, IMAGE_PADDING, paint)
        canvas.restore()
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

}