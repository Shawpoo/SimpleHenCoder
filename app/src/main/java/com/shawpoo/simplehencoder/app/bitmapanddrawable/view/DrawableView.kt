package com.shawpoo.simplehencoder.app.bitmapanddrawable.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View
import com.shawpoo.simplehencoder.app.ext.dp

/**
 * @author: wuxiaopeng
 * @date: 2021/3/30
 * @desc:
 */
class DrawableView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

//    private val drawable = ColorDrawable(Color.RED)
    private val drawable = MeshDrawable()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawable.setBounds(50.dp.toInt(), 80.dp.toInt(), width, height)
        drawable.draw(canvas)
    }

}