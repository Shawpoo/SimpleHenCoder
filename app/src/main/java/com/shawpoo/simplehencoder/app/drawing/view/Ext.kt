package com.shawpoo.simplehencoder.app.drawing.view

import android.content.res.Resources
import android.util.TypedValue

/**
 * @author: wuxiaopeng
 * @date: 2021/3/20
 * @desc:
 */

val Float.px
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )