package com.shawpoo.simplehencoder.app.ext

import android.content.res.Resources
import android.util.TypedValue

/**
 * @author: wuxiaopeng
 * @date: 2021/3/20
 * @desc:
 */
// xx.dp 5f.dp 表示5dp所对应的像素值
val Float.dp
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this,
        Resources.getSystem().displayMetrics)

val Int.dp
    get() = this.toFloat().dp