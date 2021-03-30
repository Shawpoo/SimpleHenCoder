package com.shawpoo.simplehencoder.app.text.sample

/**
 * @author: wuxiaopeng
 * @date: 2021/3/29
 * @desc:
 */
data class ProductData(
    val code: Int,
    val `data`: List<ProductItem>,
    val hasMore: Boolean,
    val msg: String
)

data class ProductItem(
    val coverImage: String,
    val feeText: String,
    val id: String,
    val link: String,
    val monthSales: String,
    val originPrice: String,
    val price: String,
    val shopName: String,
    val tab: String,
    val title: String
)