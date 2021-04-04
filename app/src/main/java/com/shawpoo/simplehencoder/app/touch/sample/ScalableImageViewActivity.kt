package com.shawpoo.simplehencoder.app.touch.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shawpoo.simplehencoder.app.R

/**
 * @author: wuxiaopeng
 * @date: 2021/4/4
 * @desc:
 */
class ScalableImageViewActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scalable_image_view)
    }

    companion object {
        fun open(context: Context) {
            var intent = Intent(context, ScalableImageViewActivity::class.java)
            context.startActivity(intent)
        }
    }
}