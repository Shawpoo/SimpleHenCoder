package com.shawpoo.simplehencoder.app.custom.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.shawpoo.simplehencoder.app.R
import com.shawpoo.simplehencoder.app.ext.dp

/**
 * @author: wuxiaopeng
 * @date: 2021/3/29
 * @desc:
 */

private val TEXT_SIZE = 12.dp
private val TEXT_MARGIN = 8.dp
private val HORIZONTAL_OFFSET = 5.dp
private val VERTICAL_OFFSET = 23.dp
private val EXTRA_VERTICAL_OFFSET = 18.dp

class MaterialEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var floatingLabelShown = false
    var floatingLabelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }
    private val animator by lazy {
        ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0f, 1f)
    }

    var useFloatingLabel = false
        set(value) {
            if (field != value) {
                field = value
                if (field) {
                    setPadding(paddingLeft, (paddingTop + TEXT_SIZE + TEXT_MARGIN).toInt(),
                        paddingRight, paddingBottom)
                } else {
                    setPadding(paddingLeft, (paddingTop - TEXT_SIZE - TEXT_MARGIN).toInt(),
                        paddingRight, paddingBottom)
                }
            }
        }

    init {
        paint.textSize = TEXT_SIZE

        //        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        //        useFloatingLabel =
        //            typeArray.getBoolean(R.styleable.MaterialEditText_userFloatingLabel, true)
        val typeArray = context.obtainStyledAttributes(attrs, intArrayOf(R.attr.userFloatingLabel))
        useFloatingLabel = typeArray.getBoolean(0, true)
        typeArray.recycle()
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int,
        lengthAfter: Int) {
        if (floatingLabelShown && text.isNullOrEmpty()) {
            floatingLabelShown = false
            animator.reverse() // 动画反向执行
        } else if (!floatingLabelShown && !text.isNullOrEmpty()) {
            floatingLabelShown = true
            animator.start()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.alpha = (floatingLabelFraction * 0xff).toInt()
        val currentVerticalValue =
            VERTICAL_OFFSET + EXTRA_VERTICAL_OFFSET * (1 - floatingLabelFraction)
        canvas.drawText(hint.toString(), HORIZONTAL_OFFSET, currentVerticalValue, paint)
    }

}