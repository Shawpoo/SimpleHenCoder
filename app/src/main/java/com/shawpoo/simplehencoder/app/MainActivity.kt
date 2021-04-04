package com.shawpoo.simplehencoder.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.postDelayed
import com.shawpoo.simplehencoder.app.animation.sample.SampleAnimationActivity
import com.shawpoo.simplehencoder.app.bitmapanddrawable.sample.DrawableActivity
import com.shawpoo.simplehencoder.app.layout.sample.SampleLayoutActivity
import com.shawpoo.simplehencoder.app.layout.sample.TagLayoutActivity
import com.shawpoo.simplehencoder.app.text.sample.ProductListActivity
import com.shawpoo.simplehencoder.app.touch.sample.ScalableImageViewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_product_List.setOnClickListener {
            ProductListActivity.open(this)
        }

        btn_drawable_and_bitmap.setOnClickListener {
            TagLayoutActivity.open(this)
        }

        btn_scalable_image_view.setOnClickListener {
            ScalableImageViewActivity.open(this)
        }

    }
}