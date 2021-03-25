package com.shawpoo.simplehencoder.app

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.shawpoo.simplehencoder.app.xfermode.sample.XfermodeListActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_multilinetextview.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_xfermode.setOnClickListener {
            XfermodeListActivity.open(this)
        }

        val url =
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1206601717,1553984173&fm=26&gp=0.jpg"

        Glide.with(this).asBitmap().load(url).into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                item_smt.setBitmap(resource)
                item_smt.setText("阿德拉打扫房间啊水电费假啦撒旦法两三点解放啦惊世毒妃逻辑啊大法师地方撒旦法")
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })
    }
}