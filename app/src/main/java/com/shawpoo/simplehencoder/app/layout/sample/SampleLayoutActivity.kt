package com.shawpoo.simplehencoder.app.layout.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shawpoo.simplehencoder.app.R

/**
 * @author: wuxiaopeng
 * @date: 2021/3/31
 * @desc:
 */
class SampleLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_custom)
    }

    companion object {
        fun open(context: Context) {
            var intent = Intent(context, SampleLayoutActivity::class.java)
            context.startActivity(intent)
        }
    }

}