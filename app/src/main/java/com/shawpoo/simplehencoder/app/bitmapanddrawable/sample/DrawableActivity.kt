package com.shawpoo.simplehencoder.app.bitmapanddrawable.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shawpoo.simplehencoder.app.R

/**
 * @author: wuxiaopeng
 * @date: 2021/3/30
 * @desc:
 */
class DrawableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_view)
    }

    companion object {
        fun open(context: Context) {
            var intent = Intent(context, DrawableActivity::class.java)
            context.startActivity(intent)
        }
    }

}