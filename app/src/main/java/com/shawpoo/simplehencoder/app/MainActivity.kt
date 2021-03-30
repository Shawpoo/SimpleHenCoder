package com.shawpoo.simplehencoder.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.postDelayed
import com.shawpoo.simplehencoder.app.animation.sample.SampleAnimationActivity
import com.shawpoo.simplehencoder.app.text.sample.ProductListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_product_List.setOnClickListener {
            ProductListActivity.open(this)
        }

    }
}