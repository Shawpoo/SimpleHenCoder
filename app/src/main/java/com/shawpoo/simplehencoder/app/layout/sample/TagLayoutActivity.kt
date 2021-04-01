package com.shawpoo.simplehencoder.app.layout.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shawpoo.simplehencoder.app.R

/**
 * @author: wuxiaopeng
 * @date: 2021/4/1
 * @desc:
 */
class TagLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_layout)
    }

    companion object {
        fun open(context: Context) {
            var intent = Intent(context, TagLayoutActivity::class.java)
            context.startActivity(intent)
        }
    }

}