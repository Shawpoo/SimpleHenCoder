package com.shawpoo.simplehencoder.app.touch.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shawpoo.simplehencoder.app.R

/**
 * @author: wuxiaopeng
 * @date: 2021/4/5
 * @desc:
 */
class MultiTouchViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_touch_view)
    }

    companion object {
        fun open(context: Context) {
            var intent = Intent(context, MultiTouchViewActivity::class.java)
            context.startActivity(intent)
        }
    }

}