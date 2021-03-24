package com.shawpoo.simplehencoder.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shawpoo.simplehencoder.app.xfermode.sample.XfermodeListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_xfermode.setOnClickListener {
            XfermodeListActivity.open(this)
        }
    }
}