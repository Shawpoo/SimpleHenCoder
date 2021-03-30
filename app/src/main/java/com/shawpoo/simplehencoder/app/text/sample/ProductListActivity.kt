package com.shawpoo.simplehencoder.app.text.sample

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.gson.Gson
import com.shawpoo.simplehencoder.app.R
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.activity_product_list.rv_list
import kotlinx.android.synthetic.main.activity_xfermode_list.*
import java.io.IOException
import java.nio.charset.Charset

/**
 * @author: wuxiaopeng
 * @date: 2021/3/29
 * @desc:
 */
class ProductListActivity : AppCompatActivity() {

    private var mList = mutableListOf<ProductItem>()
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        Thread(Runnable { initData() }).start()

    }

    private fun initView() {
        adapter = ItemAdapter(this, mList)
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.itemAnimator = DefaultItemAnimator()
        rv_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_list.adapter = adapter
    }

    private fun initData() {
        var json = ""
        try {
            val input = this.assets.open("data.json")
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            json = String(buffer, Charset.forName("utf-8"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (!json.isNullOrEmpty()) {
            var productData: ProductData = Gson().fromJson(json, ProductData::class.java)
            mList.clear()
            mList.addAll(productData.data)
        }

        runOnUiThread {
            initView()
        }
    }

    class ItemAdapter(private val context: Context, private val data: MutableList<ProductItem>) :
        RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = data.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (holder != null && holder is ViewHolder) {
                val item = data[position]
                holder.priceText.text = item.price
                holder.oldPriceText.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG;
                holder.oldPriceText.text = item.originPrice
                holder.shopNameText.text = item.shopName
                Glide.with(context).load(item.coverImage).into(holder.productImage)
                Glide.with(context).asBitmap().load(item.tab).override(30)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onLoadCleared(placeholder: Drawable?) {

                        }

                        override fun onResourceReady(resource: Bitmap,
                            transition: Transition<in Bitmap>?) {
                            holder.titleText.setBitmap(resource)
                            holder.titleText.setText(item.title)
                        }

                    })
            }
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val productImage: ImageView = view.findViewById(R.id.iv_product)
            val titleText: SimpleMultilineTextView = view.findViewById(R.id.smt_title)
            val priceText: TextView = view.findViewById(R.id.tv_price)
            val oldPriceText: TextView = view.findViewById(R.id.tv_old_price)
            val shopNameText: TextView = view.findViewById(R.id.tv_shop_name)
        }

    }

    companion object {
        fun open(context: Context) {
            var intent = Intent(context, ProductListActivity::class.java)
            context.startActivity(intent)
        }
    }

}

