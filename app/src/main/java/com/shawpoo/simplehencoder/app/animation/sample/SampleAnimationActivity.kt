package com.shawpoo.simplehencoder.app.animation.sample

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.content.Context
import android.content.Intent
import android.graphics.PointF
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shawpoo.simplehencoder.app.R
import com.shawpoo.simplehencoder.app.animation.view.PointFEvaluator
import com.shawpoo.simplehencoder.app.animation.view.ProvinceEvaluator
import com.shawpoo.simplehencoder.app.ext.dp
import kotlinx.android.synthetic.main.activity_sample_animation.*

/**
 * @author: wuxiaopeng
 * @date: 2021/3/28
 * @desc: 动画示例
 */
class SampleAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_animation)

        //        view.animate()
        //            .translationX(200.dp)
        //            .translationY(100.dp)
        //            .alpha(0.5f)
        //            .scaleX(2f)
        //            .scaleY(2f)
        //            .rotation(30f)
        //            .startDelay = 1000

        // 属性动画，修改「自定义属性」的动画
        //        ObjectAnimator.ofFloat(view, "radius", 150.dp).apply {
        //            startDelay = 1000
        //            start()
        //        }

        //        val topFlipAnimator = ObjectAnimator.ofFloat(view, "topFlip", -60f).apply {
        //            duration = 1000
        //        }
        //
        //        val bottomFlipAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 60f).apply {
        //            startDelay = 200
        //            duration = 1000
        //        }
        //
        //        val flipRotationAnimator = ObjectAnimator.ofFloat(view, "flipRotation", 270f).apply {
        //            startDelay = 200
        //            duration = 1000
        //        }
        //
        //        AnimatorSet().apply {
        //            playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator)
        //            startDelay = 1000
        //            start()
        //        }

        //        var topFlipHolder = PropertyValuesHolder.ofFloat("topFlip", -60f)
        //        val bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip", 60f)
        //        val flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation", 270f)
        //
        //        ObjectAnimator.ofPropertyValuesHolder(view, topFlipHolder, bottomFlipHolder, flipRotationHolder).apply {
        //            startDelay = 1000
        //            duration = 2000
        //            start()
        //        }

        /*val length = 200.dp
        // 关键帧，通过设置关键帧，来设置动画速度的效果
        val keyframe1 = Keyframe.ofFloat(0f, 0f)
        val keyframe2 = Keyframe.ofFloat(0.2f, 0.4f * length)
        val keyframe3 = Keyframe.ofFloat(0.8f, 0.6f * length)
        val keyframe4 = Keyframe.ofFloat(1f, 1f * length)
        val keyframeHolder =
            PropertyValuesHolder.ofKeyframe("translationY", keyframe1, keyframe2, keyframe3,
                keyframe4)
        ObjectAnimator.ofPropertyValuesHolder(view2, keyframeHolder).apply {
            startDelay = 1000
            duration = 2000
            start()
        }*/

        //        ObjectAnimator.ofFloat().apply {
        //            // 插值器 很多种
        //            interpolator = AccelerateDecelerateInterpolator()
        //        }

        // 计算
//        ObjectAnimator.ofObject(view3, "point", PointFEvaluator(), PointF(100.dp, 200.dp)).apply {
//            startDelay = 1000
//            duration = 2000
//            start()
//        }

        // 字符串动画
        /*ObjectAnimator.ofObject(view4, "province", ProvinceEvaluator(), "澳门特别行政区").apply {
            startDelay = 1000
            duration = 10000
            start()
        }*/

        view4.animate()
            .translationY(200.dp)
            .withLayer() // 临时开始硬件离屏缓冲，动画结束则关闭

    }

    companion object {
        fun open(context: Context) {
            var intent = Intent(context, SampleAnimationActivity::class.java)
            context.startActivity(intent)
        }
    }

}