package com.shawpoo.simplehencoder.app.xfermode.sample

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shawpoo.simplehencoder.app.R
import com.shawpoo.simplehencoder.app.xfermode.view.SampleXfermodeView
import kotlinx.android.synthetic.main.activity_xfermode_list.*

/**
 * @author: wuxiaopeng
 * @date: 2021/3/24
 * @desc: Xfermode各种模式的应用展示
 */
class XfermodeListActivity : AppCompatActivity() {

    private val mModeMap =
        mutableMapOf(PorterDuff.Mode.ADD to "ADD", PorterDuff.Mode.CLEAR to "CLEAR",
            PorterDuff.Mode.DARKEN to "DARKEN", PorterDuff.Mode.SRC to "SRC",
            PorterDuff.Mode.SRC_IN to "SRC_IN", PorterDuff.Mode.SRC_OUT to "SRC_OUT",
            PorterDuff.Mode.SRC_OVER to "SRC_OVER", PorterDuff.Mode.SRC_ATOP to "SRC_ATOP",
            PorterDuff.Mode.SCREEN to "SCREEN", PorterDuff.Mode.DST to "DST",
            PorterDuff.Mode.DST_ATOP to "DST_ATOP", PorterDuff.Mode.DST_IN to "DST_IN",
            PorterDuff.Mode.DST_OUT to "DST_OUT", PorterDuff.Mode.DST_OVER to "DST_OVER",
            PorterDuff.Mode.LIGHTEN to "LIGHTEN", PorterDuff.Mode.MULTIPLY to "MULTIPLY",
            PorterDuff.Mode.OVERLAY to "OVERLAY", PorterDuff.Mode.XOR to "XOR")

    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xfermode_list)

        initView()
    }

    private fun initView() {
        adapter = ItemAdapter(mModeMap)
        rv_list.layoutManager = GridLayoutManager(this, 3)
        rv_list.itemAnimator = DefaultItemAnimator()
        rv_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_list.adapter = adapter
    }

    class ItemAdapter(private val data: MutableMap<PorterDuff.Mode, String>) :
        RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

        private val list = PorterDuff.Mode.values()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_xfermode_layout, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (holder != null && holder is ViewHolder) {
                holder.xfermodeView.setXfermode(list[position])
                holder.modeText.text = data[list[position]]
            }
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val modeText: TextView = view.findViewById(R.id.tv_mode)
            val xfermodeView: SampleXfermodeView = view.findViewById(R.id.xv_mode)
        }

    }

    companion object {
        fun open(context: Context) {
            var intent = Intent(context, XfermodeListActivity::class.java)
            context.startActivity(intent)
        }
    }

}