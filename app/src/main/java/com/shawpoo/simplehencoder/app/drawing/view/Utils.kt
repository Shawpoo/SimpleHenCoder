package com.shawpoo.simplehencoder.app.drawing.view

import android.content.res.Resources
import android.util.TypedValue

/**
 * @author: wuxiaopeng
 * @date: 2021/3/20
 * @desc:
 */
fun dp2px(value: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value,
        Resources.getSystem().displayMetrics
    )
}