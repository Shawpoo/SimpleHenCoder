package com.shawpoo.simplehencoder.app.animation.view

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.shawpoo.simplehencoder.app.ext.dp

/**
 * @author: wuxiaopeng
 * @date: 2021/3/28
 * @desc:
 */
private val provinces =
    listOf("北京市", "天津市", "上海市", "重庆市", "河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省",
        "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省",
        "青海省", "台湾省", "内蒙古自治区", "广西壮族自治区", "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区", "香港特别行政区", "澳门特别行政区")

class ProvinceView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 50.dp
        textAlign = Paint.Align.CENTER
    }

    var province = provinces[0]
        set(value) {
            field = value
            invalidate()
        }

    init {
//        setLayerType(LAYER_TYPE_HARDWARE,null) // 开启view的离屏缓冲，并使用硬件绘制
//        setLayerType(LAYER_TYPE_SOFTWARE,null) // 开启view的离屏缓冲，并使用软件绘制
//        setLayerType(LAYER_TYPE_NONE,null) // 关闭view的离屏缓冲
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawText(province, width / 2f, height / 2f, paint)
    }

}

class ProvinceEvaluator : TypeEvaluator<String> {
    override fun evaluate(fraction: Float, startValue: String, endValue: String): String {
        val startIndex = provinces.indexOf(startValue)
        val endIndex = provinces.indexOf(endValue)
        val currentIndex = startIndex + (endIndex - startIndex) * fraction
        return provinces[currentIndex.toInt()]
    }

}