package com.shawpoo.simplehencoder.app.drawing.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View
import com.shawpoo.simplehencoder.app.ext.dp

/**
 * @author: wuxiaopeng
 * @date: 2021/3/20
 * @desc:
 */

val R = 100f.dp

class TestView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    lateinit var pathMeasure: PathMeasure

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        // 顺时针(CW)：clockwise， 逆时针(CCW)：counter-clockwise
        path.addCircle(width / 2f, height / 2f, R, Path.Direction.CCW)
        path.addRect(width / 2f - R, height / 2f, width / 2f + R, height / 2f + 2 * R,
            Path.Direction.CCW)
        path.addCircle(width / 2f, height / 2f, R * 1.5f, Path.Direction.CCW)
        pathMeasure = PathMeasure(path, false)
        path.fillType = Path.FillType.EVEN_ODD // 填充规则
    }

    override fun onDraw(canvas: Canvas) {
        // 画线
        // canvas.drawLine(100f, 100f, 200f, 200f, paint)

        // 画圆
        // canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)

        // 根据path画图形
        canvas.drawPath(path, paint)
    }

}